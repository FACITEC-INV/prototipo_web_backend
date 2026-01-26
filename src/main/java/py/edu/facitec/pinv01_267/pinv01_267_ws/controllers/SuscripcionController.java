package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.SuscripcionDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.SuscripcionService;

@RestController
@RequestMapping(path = "/api/suscripciones")
public class SuscripcionController {

  @Autowired
  private SuscripcionService susSer;

  @PostMapping("/save")
  public ResponseEntity<ResponseDto<SuscripcionDto>> add(@RequestBody SuscripcionDto suscripcionDto) {
    SuscripcionDto result = susSer.save(suscripcionDto);
    return ResponseEntity.ok(
        ResponseDto.<SuscripcionDto>builder()
            .success(true)
            .response(result)
            .build());
  }
}
