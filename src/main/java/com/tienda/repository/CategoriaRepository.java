
package com.tienda.repository;

import com.tienda.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*import org.springframework.stereotype.Repository; */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // Método personalizado proporcionado por Spring Data JPA (Query Method)
    public List<Categoria> findByActivoTrue();
    
    // IMPORTANTE: NO debes redefinir findById. Spring ya lo proporciona 
    // automáticamente y devuelve Optional<Categoria>. 
    // La línea que tenías: public Object findById(Long idCategoria);
    // DEBE SER ELIMINADA para que el servicio funcione correctamente.

}