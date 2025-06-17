package py.edu.facitec.pinv01_267.pinv01_267_ws.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
  @SequenceGenerator(name = "lecturas_seq", sequenceName = "lecturas_seq", allocationSize = 50)
  private long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private Dispositivo dispositivo;

  private double ph;
  private double od;
  private double con;
  private double tsd;
  private double tur;
  private double tem;
  private LocalDateTime fecha;

}
