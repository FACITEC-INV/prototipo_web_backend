package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.LecturaDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.LecturasReveicedDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Lectura;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.DispositivoRepository;

@Service
@NoArgsConstructor
public class LecturaService {

  @Autowired
  private DispositivoRepository disRep;
  @PersistenceContext
  EntityManager entity;
  @Autowired
  private DispositovoService dispSer;

  // TODO: enviar la fecha de la última lectura para sincronización

  @Transactional
  public ResponseDto addService(LecturasReveicedDto lecReq) {
    final int BATCH_SISE = 50;
    UUID dispositivoId = UUID.fromString(lecReq.getDispositivo_id());
    Optional<Dispositivo> disp = disRep.findById(dispositivoId);
    if (disp.isEmpty()) {
      return new ResponseDto(false, "El dispositivo no registrado");
    }
    int iteration = 0;
    for (LecturaDto l : lecReq.getLecturas()) {
      Lectura lec = new Lectura(disp.get(), l.getPh(), l.getOd(), l.getCon(), l.getTsd(),
          l.getTur(), l.getTem(), l.getFecha());
      entity.persist(lec);
      iteration++;
      if (iteration % BATCH_SISE == 0) {
        entity.flush();
        entity.clear();
      }
    }
    entity.flush();
    entity.clear();
    dispSer.lastConectionUpdate(disp.get());
    return new ResponseDto(true, "Guardado correcto");
  }
}
