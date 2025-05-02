package py.edu.facitec.pinv01_267.pinv01_267_ws.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "suscripciones")
public class Suscripcion {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suscripciones_seq")
  @SequenceGenerator(name = "suscripciones_seq", sequenceName = "suscripciones_seq", allocationSize = 1)
  private long id;
  @Column(nullable = false, unique = true)
  private String nombre;
  @Column(nullable = false, unique = true)
  private String correo;
  private String organizacion;
}
