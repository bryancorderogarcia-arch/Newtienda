package com.tienda.controller;

import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;


@Controller
public class IndexController {
    
    // Las últimas versiones de Spring, recomiendan utilziar final y contructor en lugar de @autowired
    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    
    // (Spring inyecta automáticamente)
    public IndexController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }
    
    @GetMapping("/")
    public String cargarPaginaInicio(Model model) {
        var lista = productoService.getProductos(true);
        model.addAttribute("productos", lista);
        var categorias = categoriaService.getCategoria(true);
        model.addAttribute("categorias", categorias);
        return "/index";
    }
    
    @GetMapping("/consultas/{idCategoria}")
    public String listado(@PathVariable("idCategoria") Integer idCategoria, Model model) {
        model.addAttribute("idCategoriaActual", idCategoria);
        Long idCategoriaLong = Long.valueOf(idCategoria);
        
        Optional<com.tienda.domain.Categoria> categoriaOptional = categoriaService.getCategoria(idCategoriaLong);
        if (categoriaOptional.isEmpty()) { 
             model.addAttribute("productos", Collections.emptyList());
        } else {
            var categoria = categoriaOptional.get();
            var productos = categoria.getProductos(); 
            model.addAttribute("productos", productos);
        }
        var categorias = categoriaService.getCategoria(true);
        model.addAttribute("categorias", categorias);
        return "/index";

      }  }