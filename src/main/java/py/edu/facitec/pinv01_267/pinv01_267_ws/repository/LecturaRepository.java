package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Lectura;

public interface LecturaRepository extends JpaRepository<Lectura, Long> {
  @Query("SELECT l FROM Lectura l WHERE l.dispositivo.id = :id AND l.fecha BETWEEN :fechaDesde AND :fechaHasta")
  List<Lectura> findLecturasByDispositivoIdAndFechaBetween(
      @Param("id") UUID dispositivoId,
      @Param("fechaDesde") LocalDateTime fechaDesde,
      @Param("fechaHasta") LocalDateTime fechaHasta);
}
