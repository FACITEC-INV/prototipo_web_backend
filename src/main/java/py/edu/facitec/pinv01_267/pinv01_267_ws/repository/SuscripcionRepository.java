package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Suscripcion;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {

  /**
   * Buscar las suscripciones por un término de búsqueda.
   * 
   * @param term
   * @return {Suscipcion} Lista de suscripciones.
   */
  @Query("SELECT s FROM Suscripcion s WHERE " +
      "LOWER(s.nombre) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
      "LOWER(s.organizacion) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
      "LOWER(s.correo) LIKE LOWER(CONCAT('%', :term, '%'))")
  List<Suscripcion> searchByTerm(@Param("term") String term);
}
