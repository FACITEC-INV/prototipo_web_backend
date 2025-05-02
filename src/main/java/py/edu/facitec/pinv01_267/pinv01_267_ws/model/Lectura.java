package py.edu.facitec.pinv01_267.pinv01_267_ws.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "lecturas")
public class Lectura {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecturas_seq")
  @SequenceGenerator(name = "lecturas_seq", sequenceName = "lecturas_seq", allocationSize = 1)
  private long id;
  @ManyToOne
  @JoinColumn(nullable = false)
  private Dispositivo dispositivo;
  private String ph;
  private String tod;
  private String con;
  private String sod;
  private String tem;
  private Date fecha;
}
