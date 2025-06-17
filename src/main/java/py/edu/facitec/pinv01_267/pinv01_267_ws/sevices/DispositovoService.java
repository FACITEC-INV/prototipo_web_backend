package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.DispositivoDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.exception.EntityNotFoundException;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.DispositivoRepository;

@Service
@Transactional
public class DispositovoService {

  @Autowired
  private DispositivoRepository disRep;
  @Autowired
  private ModelMapper modelMapper;

  public DispositivoDto save(DispositivoDto dispositivoDto) {
    Dispositivo saved = disRep.save(convertToEntity(dispositivoDto));
    return convertToDto(saved);
  }

  public List<DispositivoDto> findAll() {
    List<Dispositivo> dispositivos = disRep.findAll();
    return dispositivos.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  public DispositivoDto getById(String id) {
    UUID dispositivoId = UUID.fromString(id);
    return convertToDto(getById(dispositivoId));
  }

  Dispositivo getById(UUID dispositivoId) {
    Dispositivo disp = disRep.findById(dispositivoId)
        .orElseThrow(() -> new EntityNotFoundException("Dispositivo no registrado"));
    return disp;
  }

  // TODO: Enviar el id al dispositivo para la primera conexión

  public void lastConectionUpdate(Dispositivo disp) {
    disp.setUltimaConexion(LocalDateTime.now());
    disRep.save(disp);
  }

  private DispositivoDto convertToDto(Dispositivo dispositivo) {
    return modelMapper.map(dispositivo, DispositivoDto.class);
  }

  private Dispositivo convertToEntity(DispositivoDto dispositivoDto) {
      return modelMapper.map(dispositivoDto, Dispositivo.class);
  }
}
