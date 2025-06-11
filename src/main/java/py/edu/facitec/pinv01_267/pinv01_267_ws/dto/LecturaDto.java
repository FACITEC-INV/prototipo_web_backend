package py.edu.facitec.pinv01_267.pinv01_267_ws.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LecturaDto {
  private double ph;
  private double od;
  private double con;
  private double tsd;
  private double tur;
  private double tem;
  private LocalDateTime fecha;
}
