package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Lectura;

public interface LecturaRepository extends JpaRepository<Lectura, Long> {
  @Query("""
      SELECT new py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto(
          YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha), HOUR(l.fecha),
          AVG(l.ph), AVG(l.od), AVG(l.con), AVG(l.tsd), AVG(l.tur), AVG(l.tem)
      )
      FROM Lectura l
      WHERE l.fecha BETWEEN :inicio AND :fin
        AND l.dispositivo.id = :dispositivoId
      GROUP BY YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha), HOUR(l.fecha)
      ORDER BY YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha), HOUR(l.fecha)
  """)
  List<PromedioLecturaDto> promedioPorHora(LocalDateTime inicio, LocalDateTime fin, UUID dispositivoId);

  @Query("""
      SELECT new py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto(
          YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha),
          AVG(l.ph), AVG(l.od), AVG(l.con), AVG(l.tsd), AVG(l.tur), AVG(l.tem)
      )
      FROM Lectura l
      WHERE l.fecha BETWEEN :inicio AND :fin
        AND l.dispositivo.id = :dispositivoId
      GROUP BY YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha)
      ORDER BY YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha)
  """)
  List<PromedioLecturaDto> promedioPorDia(LocalDateTime inicio, LocalDateTime fin, UUID dispositivoId);

  @Query("""
      SELECT new py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto(
          YEAR(l.fecha), MONTH(l.fecha),
          AVG(l.ph), AVG(l.od), AVG(l.con), AVG(l.tsd), AVG(l.tur), AVG(l.tem)
      )
      FROM Lectura l
      WHERE l.fecha BETWEEN :inicio AND :fin
        AND l.dispositivo.id = :dispositivoId
      GROUP BY YEAR(l.fecha), MONTH(l.fecha)
      ORDER BY YEAR(l.fecha), MONTH(l.fecha)
  """)
  List<PromedioLecturaDto> promedioPorMes(LocalDateTime inicio, LocalDateTime fin, UUID dispositivoId);
}
