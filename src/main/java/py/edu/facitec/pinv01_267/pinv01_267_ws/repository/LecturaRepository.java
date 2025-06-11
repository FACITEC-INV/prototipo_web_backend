package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Lectura;

public interface LecturaRepository extends CrudRepository<Lectura, Long> {

  @Override
  @RestResource(exported = false)
  Iterable<Lectura> findAll();

  @Query("SELECT l FROM Lectura l WHERE l.dispositivo.id = :id AND l.fecha BETWEEN :fechaDesde AND :fechaHasta")
  List<Lectura> findLecturasByDispositivoIdAndFechaBetween(
      @Param("id") UUID dispositivoId,
      @Param("fechaDesde") LocalDateTime fechaDesde,
      @Param("fechaHasta") LocalDateTime fechaHasta);
}
