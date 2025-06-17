package py.edu.facitec.pinv01_267.pinv01_267_ws.dto;

import java.util.List;

import lombok.Data;

@Data
public class LecturasReveicedDto {

  private String dispositivoId;
  private List<LecturaDto> lecturas;

}
