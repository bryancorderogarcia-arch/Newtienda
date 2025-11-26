package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductoRepository extends JpaRepository<Producto, Long>{
 public List<Producto> findByActivoTrue();
}
