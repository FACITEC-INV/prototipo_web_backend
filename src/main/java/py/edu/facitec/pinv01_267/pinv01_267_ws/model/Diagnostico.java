package py.edu.facitec.pinv01_267.pinv01_267_ws.model;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "diagnosticos")
public class Diagnostico {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnostico_seq")
  @SequenceGenerator(name = "diagnostico_seq", sequenceName = "diagnostico_seq", allocationSize = 1)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dispositivo_id", nullable = false)
  private Dispositivo dispositivo;

  @Column(nullable = false)
  private LocalDateTime fecha;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "status", column = @Column(name = "temp_status")),
      @AttributeOverride(name = "value", column = @Column(name = "temp_value")),
      @AttributeOverride(name = "message", column = @Column(name = "temp_message"))
  })
  private ItemDiagnostico temp;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "status", column = @Column(name = "disk_status")),
      @AttributeOverride(name = "value", column = @Column(name = "disk_value")),
      @AttributeOverride(name = "message", column = @Column(name = "disk_message"))
  })
  private ItemDiagnostico disk;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "status", column = @Column(name = "power_status")),
      @AttributeOverride(name = "value", column = @Column(name = "power_value")),
      @AttributeOverride(name = "message", column = @Column(name = "power_message"))
  })
  private ItemDiagnostico power;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "status", column = @Column(name = "arduino_status")),
      @AttributeOverride(name = "value", column = @Column(name = "arduino_value")),
      @AttributeOverride(name = "message", column = @Column(name = "arduino_message"))
  })
  private ItemDiagnostico arduino;

  @PrePersist
  protected void onCreate() {
    this.fecha = LocalDateTime.now();
  }

  @Data
  @Embeddable
  public static class ItemDiagnostico {
    private String status;
    private String value;
    private String message;
  }
}
