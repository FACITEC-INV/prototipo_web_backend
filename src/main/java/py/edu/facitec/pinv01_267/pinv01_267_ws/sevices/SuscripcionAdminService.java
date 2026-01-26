package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.SuscripcionDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.exception.EntityNotFoundException;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Suscripcion;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.SuscripcionRepository;

@Service
@Transactional
public class SuscripcionAdminService extends BaseService<Suscripcion, SuscripcionDto> {

  @Autowired
  private SuscripcionRepository susRep;

  public SuscripcionAdminService() {
    super(Suscripcion.class, SuscripcionDto.class);
  }

  public SuscripcionDto save(SuscripcionDto suscripcionDto) {
    Suscripcion saved = susRep.save(convertToEntity(suscripcionDto));
    return convertToDto(saved);
  }

  public List<SuscripcionDto> findAll() {
    List<Suscripcion> suscripciones = susRep.findAll();
    return suscripciones.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public SuscripcionDto getById(Long id) {
    Suscripcion sus = susRep.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Suscripcion no registrada - id: " + id));
    return convertToDto(sus);
  }

  public List<SuscripcionDto> findByTem(String term) {
    List<Suscripcion> suscripciones = susRep.searchByTerm(term);
    return suscripciones.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public boolean delete(Long id) {
    try {
      susRep.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new Error("Error al elimanar la suscripcion - id:" + id);
    }
  }

}
