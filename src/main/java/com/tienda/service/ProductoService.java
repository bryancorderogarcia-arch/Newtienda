/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoService {

     @Autowired
    private ProductoRepository productoRepository;
    
    /**
     * Recupera una lista de categorías, filtrando opcionalmente por estado activo.
     * @param activo Si es true, solo devuelve categorías activas. Si es false, devuelve todas.
     * @return Lista de Productos.
     */
    @Transactional(readOnly=true)
    public List<Producto> getProductos(boolean activo) {
        if (activo) {
            // Se asume que findByActivoTrue está definido en el Repositorio.
            return productoRepository.findByActivoTrue();
        }
        return productoRepository.findAll();
    }
    
    /**
     * Recupera una única categoría por su ID.
     * @param idProducto El ID de la categoría a buscar (Long).
     * @return El objeto Producto si existe, o null.
     */
    @Transactional(readOnly = true)
    public Producto getProducto(Long idProducto) {
        // Corrección en la línea 51 (aproximada): Uso directo de findById().orElse(null)
        // El tipo de retorno de findById es Optional<Producto>, que sí tiene el método orElse.
        return productoRepository.findById(idProducto).orElse(null);
    }

   
    /**
     * Guarda o actualiza una categoría.
     * @param producto El objeto Producto a guardar.
     */
    @Transactional
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    /**
     * Elimina una categoría.
     * @param producto El objeto Producto a eliminar.
     * @return true si la eliminación fue exitosa, false en caso de error.
     */
    @Transactional
    public boolean delete(Producto producto) {
        try {
            productoRepository.delete(producto);
            productoRepository.flush(); 
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar la categoría: " + e.getMessage());
            return false;
        }
    }

    public void delete(Integer idProducto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Optional<Producto> getProducto(Integer idProducto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void save(Producto producto, MultipartFile imagenFile) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}