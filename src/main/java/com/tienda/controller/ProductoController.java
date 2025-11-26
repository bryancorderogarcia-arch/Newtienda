package com.tienda.controller;

import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService; // Necesario para la inyección
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired; // Necesario para la inyección
import org.springframework.context.MessageSource; // Necesario para usar messageSource
import org.springframework.stereotype.Controller; // Necesario para que sea un controlador
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; // Generalmente se usa para la ruta base
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto") // Define la ruta base para todos los métodos
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    // Asumiendo que el MessageSource se llama 'messageSource'
    @Autowired
    private CategoriaService categoriaService;

    // --- MÉTODOS DEL CONTROLADOR ---

    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile, RedirectAttributes redirectAttributes) {
        // NOTA: Para que esto compile y funcione, ProductoService.save debe aceptar Producto y MultipartFile.
        productoService.save(producto, imagenFile);
        redirectAttributes.addFlashAttribute("tituloOk", CategoriaService.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/producto/listado";
    }

    @PostMapping("/eliminar")
    // CORRECCIÓN: Usamos Integer para idProducto para coincidir con la firma del servicio (como se ve en tus imágenes).
    public String eliminar(@RequestParam Integer idProducto, RedirectAttributes redirectAttributes) { 
        String titulo = "tituloOk";
        String detalle = "mensaje.eliminado";
        try {
            // Asumiendo que ProductoService.delete acepta Integer id
            productoService.delete(idProducto); 
        } catch (IllegalArgumentException e) {
            titulo = "error"; 
            detalle = "producto.error01";
        } catch (IllegalStateException e) {
            titulo = "error"; 
            // CORRECCIÓN: Usamos 'producto.error02' en lugar de 'categoria.error02'
            detalle = "producto.error02"; 
        } catch (Exception e) {
            titulo = "error"; 
            detalle = "producto.error03";
        }

        redirectAttributes.addFlashAttribute(titulo, CategoriaService.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    // CORRECCIÓN: Usamos Integer para idProducto para mantener la consistencia.
    public String modificar(@PathVariable("idProducto") Integer idProducto, Model model, RedirectAttributes redirectAttributes) { 
        // Asumiendo que ProductoService.getProducto devuelve Optional<Producto>
        Optional<Producto> productoOpt = productoService.getProducto(idProducto);
        
        if (productoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", CategoriaService.getMessage("producto.error01", null, Locale.getDefault()));
            return "redirect:/producto/listado";
        }
        model.addAttribute("producto", productoOpt.get());
        return "redirect:/producto/modifica"; // Es 'redirect' si viene de un listado y quieres evitar un GET en cascada
    }
}