package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.LecturaDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.LecturasReveicedDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.exception.BadRequestException;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Lectura;

@Service
public class LecturaService extends BaseService<Lectura, LecturaDto>{

  @PersistenceContext
  EntityManager entity;
  @Autowired
  private DispositovoAdminService dispSer;

  public LecturaService() {
    super(Lectura.class, LecturaDto.class);
  }

  // TODO: enviar la fecha de la última lectura para sincronización

  @Transactional
  public void addService(LecturasReveicedDto lecReq) {
    if (lecReq.getLecturas() == null || lecReq.getLecturas().isEmpty()) {
      throw new BadRequestException("No hay datos de lecturas");
    }

    UUID dispositivoId = UUID.fromString(lecReq.getDispositivoId());
    Dispositivo disp = dispSer.getById(dispositivoId);

    int BATCH_SIZE = 50;
    int iteration = 0; 
    for (LecturaDto lecDto : lecReq.getLecturas()) {
      Lectura lec = convertToEntity(lecDto);
      lec.setDispositivo(disp);
      entity.persist(lec);
      iteration++;
      if (iteration % BATCH_SIZE == 0) {
        entity.flush();
        entity.clear();
      }
    }

    entity.flush();
    entity.clear();
    dispSer.lastConectionUpdate(disp);
  }
}
