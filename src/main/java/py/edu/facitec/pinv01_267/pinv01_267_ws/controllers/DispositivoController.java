package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto<DispositivoDto>> get(@PathVariable String id) {
        DispositivoDto result = dispSer.getById(id);
        return ResponseEntity.ok(
            ResponseDto.<DispositivoDto>builder()
                .success(true)
                .response(result)
                .build()
        );
    }
 
    @PostMapping("/save")
    public ResponseEntity<ResponseDto<DispositivoDto>> add(@RequestBody DispositivoDto dispositivoDto) {
        DispositivoDto result = dispSer.save(dispositivoDto);
        return ResponseEntity.ok(
            ResponseDto.<DispositivoDto>builder()
                .success(true)
                .response(result)
                .build()
        );
    }

}
