/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 * Entidad que representa un producto de la tienda.
 * Utiliza Lombok (@Data) para generar getters, setters, toString, equals y hashCode.
 */
@Entity
@Data
@Table(name = "producto")
public class Producto implements Serializable {
    
    // Serial version UID para la serialización de objetos
    private static final long serialVersionUID = 1L;
    
    // Campo ID y clave primaria (autoincremental)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto; // id_producto en la base de datos
    
    private String nombre;
    private String descripcion;
    private double precio;
    private int existencias;
    private boolean activo; // Para saber si el producto está disponible o no
    
    // Campo para guardar la RUTA ESTÁTICA de la imagen
    // La imagen física se guardará en src/main/resources/static/img/productos
    private String rutaImagen; 

    // Constructor vacío (necesario para JPA/Hibernate)
    public Producto() {
    }

    // Constructor para crear un producto sin el ID (que es autoincremental)
    public Producto(String nombre, String descripcion, double precio, int existencias, boolean activo, String rutaImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.activo = activo;
        this.rutaImagen = rutaImagen;
    }
}