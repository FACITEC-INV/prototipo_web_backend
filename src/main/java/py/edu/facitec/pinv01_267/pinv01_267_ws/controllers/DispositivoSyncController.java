package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.DiagnosticoRequestDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.DispositivoAdminDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.DiagnosticoService;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.DispositivoAdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/sync")
public class DispositivoSyncController {

  @Autowired
  private DispositivoAdminService dispSer;

  @Autowired
  private DiagnosticoService diagSer;

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto<DispositivoAdminDto>> get(@PathVariable String id) {
    DispositivoAdminDto result = dispSer.getById(id);
    return ResponseEntity.ok(
        ResponseDto.<DispositivoAdminDto>builder()
            .success(true)
            .response(result)
            .build());
  }

  @PostMapping("/status")
  public ResponseEntity<ResponseDto<Boolean>> setStatus(@RequestBody DiagnosticoRequestDto dto) {
    Boolean resp = diagSer.save(dto);
    return ResponseEntity.ok(
        ResponseDto.<Boolean>builder()
            .success(true)
            .response(resp)
            .build());
  }

}
