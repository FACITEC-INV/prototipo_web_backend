package py.edu.facitec.pinv01_267.pinv01_267_ws.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DispositivoAdminDto {
    private String id;
    private String rio;
    private String ubicacion;
    private int intervaloActualizacion;
    private LocalDateTime ultimaConexion;
}
