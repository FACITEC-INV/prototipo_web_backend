package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
     * Obtiene los promedios agrupados dinámicamente según el rango de fechas:
     * - ≤ 1 día: promedio por hora
     * - ≤ 31 días: promedio por día
     * - > 31 días: promedio por mes
     *
     * @param dispositivoId UUID del dispositivo
     * @param desde         fecha y hora inicial (formato ISO, ej: 2025-07-10T00:00)
     * @param hasta         fecha y hora final (formato ISO, ej: 2025-07-10T23:59)
     * @return Lista de promedios agrupados
     */
    @GetMapping("/promedios")
    public ResponseDto<List<PromedioLecturaDto>> obtenerPromedios(
            @RequestParam UUID dispositivoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta
    ) {
        List<PromedioLecturaDto> result = lecServ.obtenerPromedios(desde, hasta, dispositivoId);
        return ResponseDto.<List<PromedioLecturaDto>>builder()
            .success(true)
            .response(result)
            .build();
    }

}
