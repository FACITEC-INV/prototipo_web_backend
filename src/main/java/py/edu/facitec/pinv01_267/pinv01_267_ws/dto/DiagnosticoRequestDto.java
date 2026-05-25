package py.edu.facitec.pinv01_267.pinv01_267_ws.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Diagnostico;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Diagnostico.ItemDiagnostico;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class DiagnosticoRequestDto {

  // Termina en uuid para que el mapper no intente mapear al id
  // del diagnóstico. Si no lanza error de tipo uuid a long
  @NonNull
  @JsonProperty("dispositivo_id")
  private UUID dispositivoUuid;

  private DetalleDiagnosticoDto diagnostico;

  // --- CLASES INTERNAS ---

  @Data
  public static class DetalleDiagnosticoDto {
    private ItemDto temp;
    private ItemDto disk;
    private ItemDto power;
    private ItemDto arduino;
  }

  @Data
  public static class ItemDto {
    private String status;
    private String value;
    private String message;
  }

  // --- MÉTODOS DE MAPEO ---

  /** Mapeo al modelo. */
  public Diagnostico toEntity() {
    Diagnostico entity = new Diagnostico();

    if (this.diagnostico != null) {
      entity.setTemp(mapToItemDiagnostico(this.diagnostico.getTemp()));
      entity.setDisk(mapToItemDiagnostico(this.diagnostico.getDisk()));
      entity.setPower(mapToItemDiagnostico(this.diagnostico.getPower()));
      entity.setArduino(mapToItemDiagnostico(this.diagnostico.getArduino()));
    }

    return entity;
  }

  /** Mapeo al dto. */
  public static DiagnosticoRequestDto fromEntity(Diagnostico entity) {
    if (entity == null)
      return null;

    UUID dispId = (entity.getDispositivo() != null) ? entity.getDispositivo().getId() : null;
    DiagnosticoRequestDto dto = new DiagnosticoRequestDto(dispId);

    DetalleDiagnosticoDto detalle = new DetalleDiagnosticoDto();
    detalle.setTemp(mapToItemDto(entity.getTemp()));
    detalle.setDisk(mapToItemDto(entity.getDisk()));
    detalle.setPower(mapToItemDto(entity.getPower()));
    detalle.setArduino(mapToItemDto(entity.getArduino()));

    dto.setDiagnostico(detalle);
    return dto;
  }

  // --- MÉTODOS DE MAPEO INTERNO ---

  private ItemDiagnostico mapToItemDiagnostico(ItemDto dto) {
    if (dto == null)
      return null;
    ItemDiagnostico entityItem = new ItemDiagnostico();
    entityItem.setStatus(dto.getStatus());
    entityItem.setValue(dto.getValue());
    entityItem.setMessage(dto.getMessage());
    return entityItem;
  }

  private static ItemDto mapToItemDto(ItemDiagnostico entity) {
    if (entity == null)
      return null;
    ItemDto dtoItem = new ItemDto();
    dtoItem.setStatus(entity.getStatus());
    dtoItem.setValue(entity.getValue());
    dtoItem.setMessage(entity.getMessage());
    return dtoItem;
  }

}
