package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.DispositivoRepository;

@Service
public class DispositovoService {

  @Autowired
  private DispositivoRepository disRep;

  // TODO: Enviar el id al dispositivo para la primera conexión

  public void lastConectionUpdate(Dispositivo disp) {
    disp.setUltimaConexion(LocalDateTime.now());
    disRep.save(disp);
  }
}
