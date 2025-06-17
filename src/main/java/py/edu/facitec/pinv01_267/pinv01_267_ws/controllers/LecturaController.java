package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
  public ResponseEntity<ResponseDto<String>> add(@RequestBody LecturasReveicedDto lecReq) {
    lecServ.addService(lecReq);
    return ResponseEntity.ok(
        ResponseDto.<String>builder()
            .success(true)
            .response("Guardado correcto")
            .build()
    );
  }

}
