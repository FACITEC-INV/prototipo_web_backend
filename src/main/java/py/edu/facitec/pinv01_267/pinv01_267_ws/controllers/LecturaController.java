package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.LecturasReveicedDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.PromedioLecturaDto;
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

  /**
   * @param dispositivoId UUID of the device
   * @param year     Year of the readings (required)
   * @param month    Month of the readings (optional, required if day is provided)
   * @param day      Day of the readings (optional)
   * @return List of average readings grouped by the specified granularity
   */
  @GetMapping("/promedios")
  public ResponseDto<List<PromedioLecturaDto>> getAverages(
    @RequestParam UUID dispositivoId,
    @RequestParam Integer year,
    @RequestParam(required = false) Integer month,
    @RequestParam(required = false) Integer day
  ) {
    List<PromedioLecturaDto> result = lecServ.getAverages(year, month, day, dispositivoId);
    return ResponseDto.<List<PromedioLecturaDto>>builder()
      .success(true)
      .response(result)
      .build();
  }


}
