package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.DispositivoDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.DispositivoRepository;

@Service
@Transactional
public class DispositovoService extends BaseService<Dispositivo, DispositivoDto> {

  @Autowired
  private DispositivoRepository disRep;

  public DispositovoService() {
    super(Dispositivo.class, DispositivoDto.class);
  }

  public List<DispositivoDto> findAll() {
    List<Dispositivo> dispositivos = disRep.findAll();
    return dispositivos.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

}
