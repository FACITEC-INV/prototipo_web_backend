package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Lectura;

public interface LecturaRepository extends JpaRepository<Lectura, Long> {
  // Averages by hour
  @Query("SELECT new py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto(" +
          "YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha), HOUR(l.fecha), " +
          "AVG(l.ph), AVG(l.od), AVG(l.con), AVG(l.tsd), AVG(l.tur), AVG(l.tem)) " +
          "FROM Lectura l " +
          "WHERE YEAR(l.fecha) = :year AND MONTH(l.fecha) = :month AND DAY(l.fecha) = :day " +
          "AND l.dispositivo.id = :dispositivoId " +
          "GROUP BY YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha), HOUR(l.fecha) " +
          "ORDER BY YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha), HOUR(l.fecha)")
  List<PromedioLecturaDto> findAverageByHour(
      @Param("year") int year,
      @Param("month") int month,
      @Param("day") int day,
      @Param("dispositivoId") UUID dispositivoId
  );

  // Averages by day
  @Query("SELECT new py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto(" +
          "YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha), " +
          "AVG(l.ph), AVG(l.od), AVG(l.con), AVG(l.tsd), AVG(l.tur), AVG(l.tem)) " +
          "FROM Lectura l " +
          "WHERE YEAR(l.fecha) = :year AND MONTH(l.fecha) = :month " +
          "AND l.dispositivo.id = :dispositivoId " +
          "GROUP BY YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha) " +
          "ORDER BY YEAR(l.fecha), MONTH(l.fecha), DAY(l.fecha)")
  List<PromedioLecturaDto> findAverageByDay(
      @Param("year") int year,
      @Param("month") int month,
      @Param("dispositivoId") UUID dispositivoId
  );

  // Averages by month
  @Query("SELECT new py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto(" +
          "YEAR(l.fecha), MONTH(l.fecha), " +
          "AVG(l.ph), AVG(l.od), AVG(l.con), AVG(l.tsd), AVG(l.tur), AVG(l.tem)) " +
          "FROM Lectura l " +
          "WHERE YEAR(l.fecha) = :year " +
          "AND l.dispositivo.id = :dispositivoId " +
          "GROUP BY YEAR(l.fecha), MONTH(l.fecha) " +
          "ORDER BY YEAR(l.fecha), MONTH(l.fecha)")
  List<PromedioLecturaDto> findAverageByMonth(
      @Param("year") int year,
      @Param("dispositivoId") UUID dispositivoId
  );
}
