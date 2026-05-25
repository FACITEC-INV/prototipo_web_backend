package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.DiagnosticoRequestDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.exception.EntityNotFoundException;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Diagnostico;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.DiagnosticoRepository;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.DispositivoRepository;

@Service
@Transactional
public class DiagnosticoService {

  @Autowired
  private DiagnosticoRepository diagRepo;

  @Autowired
  private DispositivoRepository dispRepo;

  @Autowired
  private JavaMailSender mailSender;

  @Value("${notifications.recipients}")
  private String[] destinatarios;

  @Value("${spring.mail.username}")
  private String remitente;

  /**
   * Guarda un diagnóstico asociado a un dispositivo.
   * 
   * @param dto Objeto DTO que contiene los datos del diagnóstico a guardar,
   * @return {@Boolean} si el diagnóstico se guardó exitosamente o no,
   */
  public Boolean save(DiagnosticoRequestDto dto) {
    try {
      UUID dispositivoId = dto.getDispositivoUuid();
      if (dispositivoId == null)
        throw new EntityNotFoundException("Id del dispositivo inválido.");
      Dispositivo dispositivo = dispRepo.findById(dispositivoId)
          .orElseThrow(
              () -> new EntityNotFoundException(
                  "No se encontró el dispositivo con el id = " + dispositivoId));
      Diagnostico diag = dto.toEntity();
      diag.setDispositivo(dispositivo);
      diagRepo.save(diag);
      evaluarDiagnostico(dto, dispositivo);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Evalúa un diagnóstico en busca de condiciones críticas.
   * 
   * @param dto            DTO que contiene los detalles del diagnóstico a evaluar
   * @param {@Dispositivo} dispositivo Dispositivo diagnosticado
   */
  private void evaluarDiagnostico(DiagnosticoRequestDto dto, Dispositivo dispositivo) {
    var detalle = dto.getDiagnostico();
    if (detalle == null)
      return;

    Map<String, DiagnosticoRequestDto.ItemDto> criticos = new HashMap<>();

    Map<String, DiagnosticoRequestDto.ItemDto> diagnosticos = new HashMap<>();
    diagnosticos.put("Temperatura", detalle.getTemp());
    diagnosticos.put("Almacenamiento", detalle.getDisk());
    diagnosticos.put("Energía", detalle.getPower());
    diagnosticos.put("Arduino", detalle.getArduino());

    diagnosticos.forEach((componente, valores) -> {
      if (valores != null && "CRITICAL".equalsIgnoreCase(valores.getStatus())) {
        criticos.put(componente, valores);
      }
    });

    if (!criticos.isEmpty()) {
      notificar(dispositivo, criticos);
    }
  }

  /**
   * Envía una notificación por correo electrónico sobre fallas detectadas.
   * 
   * @param {@Dispositivo} dispositivo Dispositivo que presenta fallas
   * @param fallas         Mapa que contiene los componentes con fallas
   */
  private void notificar(Dispositivo dispositivo, Map<String, DiagnosticoRequestDto.ItemDto> fallas) {
    try {
      if (destinatarios == null || destinatarios.length == 0)
        throw new MailPreparationException("Lista de destinatarios inválida.");
      StringBuilder cuerpo = new StringBuilder();
      cuerpo.append("SISTEMA DE MONITOREO DE PINV01-267 - FACITEC\n");
      cuerpo.append("-------------------------------------------------\n\n");
      cuerpo.append("Se han detectado anomalías críticas en el siguiente nodo del sistema:\n");
      cuerpo.append("- ID Dispositivo: ").append(dispositivo.getId()).append("\n");
      cuerpo.append("- Río: ").append(dispositivo.getRio()).append("\n");
      cuerpo.append("- Ubicación: ").append(dispositivo.getUbicacion()).append("\n");
      cuerpo.append("- Última conexión: ").append(dispositivo.getUltimaConexion()).append("\n\n");
      cuerpo.append("DETALLE DE LAS FALLAS:\n");
      cuerpo.append("-------------------------------------------------\n");
      fallas.forEach((origen, detalle) -> {
        cuerpo.append("• Componente: ").append(origen).append("\n");
        cuerpo.append("- Estado: ").append(detalle.getStatus()).append("\n");
        cuerpo.append("- Valor: ").append(detalle.getValue()).append("\n");
        cuerpo.append("- Mensaje: ").append(detalle.getMessage()).append("\n");
        cuerpo.append("\n");
      });
      cuerpo.append("-------------------------------------------------\n");
      cuerpo.append("\nSe necesita verificar físicamente el equipo.");
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(remitente);
      message.setTo(destinatarios != null ? destinatarios : new String[0]);
      message.setSubject("[PINV01-267] Falla de en Dispositivo");
      message.setText(cuerpo.toString());
      mailSender.send(message);
      System.out.println("[MAIL-INFO]: Alerta de correo enviada con éxito a los destinatarios.");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }
}
