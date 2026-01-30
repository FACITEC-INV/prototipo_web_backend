package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.SuscripcionDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Suscripcion;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.SuscripcionRepository;

@Service
@Transactional
public class SuscripcionService extends BaseService<Suscripcion, SuscripcionDto> {

  @Autowired
  private SuscripcionRepository susRep;

  public SuscripcionService() {
    super(Suscripcion.class, SuscripcionDto.class);
  }

  public SuscripcionDto save(SuscripcionDto suscripcionDto) {
    Suscripcion saved = susRep.save(convertToEntity(suscripcionDto));
    return convertToDto(saved);
  }

}
