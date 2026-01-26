package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.SuscripcionDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.SuscripcionAdminService;

@RestController
@RequestMapping(path = "/api/admin/suscripciones")
public class SuscripcionAdminController {

  @Autowired
  private SuscripcionAdminService susSer;

  @GetMapping("/all")
  public ResponseEntity<ResponseDto<List<SuscripcionDto>>> getAll() {
    List<SuscripcionDto> result = susSer.findAll();
    return ResponseEntity.ok(
        ResponseDto.<List<SuscripcionDto>>builder()
            .success(true)
            .response(result)
            .build());
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<ResponseDto<SuscripcionDto>> get(@PathVariable Long id) {
    SuscripcionDto result = susSer.getById(id);
    return ResponseEntity.ok(
        ResponseDto.<SuscripcionDto>builder()
            .success(true)
            .response(result)
            .build());
  }

  @PostMapping("/save")
  public ResponseEntity<ResponseDto<SuscripcionDto>> add(@RequestBody SuscripcionDto suscripcionDto) {
    SuscripcionDto result = susSer.save(suscripcionDto);
    return ResponseEntity.ok(
        ResponseDto.<SuscripcionDto>builder()
            .success(true)
            .response(result)
            .build());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ResponseDto<String>> delete(@PathVariable Long id) {
    boolean resp = susSer.delete(id);
    return ResponseEntity.ok(
        ResponseDto.<String>builder()
            .success(resp)
            .response("Eliminado correctamente")
            .build());
  }

  @GetMapping("/search/{term}")
  public ResponseEntity<ResponseDto<List<SuscripcionDto>>> search(@PathVariable String term) {
    List<SuscripcionDto> result = susSer.findByTem(term);
    return ResponseEntity.ok(
        ResponseDto.<List<SuscripcionDto>>builder()
            .success(true)
            .response(result)
            .build());
  }
}
