package py.edu.facitec.pinv01_267.pinv01_267_ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto <T>{

  private boolean success;
  private T response;

}
