package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.LecturasReveicedDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.LecturaService;

@RestController
@RequestMapping(path = "/api/lecturas")
public class LecturaController {

  @Autowired
  LecturaService lecServ;

  @PostMapping("/add")
  public ResponseEntity<?> add(@RequestBody LecturasReveicedDto lecReq) {
    if (lecReq.getLecturas() == null || lecReq.getLecturas().isEmpty()) {
      ResponseDto rdto = new ResponseDto(false, "No hay datos de lecturas");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rdto);
    }
    ResponseDto response = lecServ.addService(lecReq);
    HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status).body(response);
  }

}
