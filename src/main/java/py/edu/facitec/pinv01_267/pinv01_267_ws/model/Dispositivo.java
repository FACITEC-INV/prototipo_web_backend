package py.edu.facitec.pinv01_267.pinv01_267_ws.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dispositivos")
public class Dispositivo {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false, unique = true)
  private String rio;
  @Column(nullable = false)
  private String ubicacion;
  @Column(nullable = false)
  private int intervaloActualizacion;
  private LocalDateTime ultimaConexion;
  

}
