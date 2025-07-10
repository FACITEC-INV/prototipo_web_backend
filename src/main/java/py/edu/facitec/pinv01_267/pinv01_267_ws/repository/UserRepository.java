package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

    /**
     * Buscar los dispositivos por un término de búsqueda.
     * Compara el termino buscado con el nombre del usuario.
     * 
     * @param term
     * @return {User} Lista de dispositivos.
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(u.username) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<User> searchByTerm(@Param("term") String term);
}
