package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.DispositivoDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.DispositovoService;

@RestController
@RequestMapping(path = "/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositovoService dispSer;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<DispositivoDto>>> getAll() {
        List<DispositivoDto> result = dispSer.findAll();
        return ResponseEntity.ok(
            ResponseDto.<List<DispositivoDto>>builder()
                .success(true)
                .response(result)
                .build()
        );
    }

}
