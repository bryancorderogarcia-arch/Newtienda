/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

     @Autowired
    private CategoriaRepository categoriaRepository;
    
    /**
     * Recupera una lista de categorías, filtrando opcionalmente por estado activo.
     * @param activo Si es true, solo devuelve categorías activas. Si es false, devuelve todas.
     * @return Lista de Categorias.
     */
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activo) {
        if (activo) {
            // Se asume que findByActivoTrue está definido en el Repositorio.
            return categoriaRepository.findByActivoTrue();
        }
        return categoriaRepository.findAll();
    }
    
    /**
     * Recupera una única categoría por su ID.
     * @param idCategoria El ID de la categoría a buscar (Long).
     * @return El objeto Categoria si existe, o null.
     */
    @Transactional(readOnly = true)
    public Categoria getCategoria(Long idCategoria) {
        // Corrección en la línea 51 (aproximada): Uso directo de findById().orElse(null)
        // El tipo de retorno de findById es Optional<Categoria>, que sí tiene el método orElse.
        return categoriaRepository.findById(idCategoria).orElse(null);
    }

   
    /**
     * Guarda o actualiza una categoría.
     * @param categoria El objeto Categoria a guardar.
     */
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    /**
     * Elimina una categoría.
     * @param categoria El objeto Categoria a eliminar.
     * @return true si la eliminación fue exitosa, false en caso de error.
     */
    @Transactional
    public boolean delete(Categoria categoria) {
        try {
            categoriaRepository.delete(categoria);
            categoriaRepository.flush(); 
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar la categoría: " + e.getMessage());
            return false;
        }
    }
}