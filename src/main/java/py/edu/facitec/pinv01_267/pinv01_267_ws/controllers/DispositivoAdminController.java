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

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.DispositivoAdminDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.DispositovoAdminService;

@RestController
@RequestMapping(path = "/api/admin/dispositivos")
public class DispositivoAdminController {

    @Autowired
    private DispositovoAdminService dispSer;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<DispositivoAdminDto>>> getAll() {
        List<DispositivoAdminDto> result = dispSer.findAll();
        return ResponseEntity.ok(
                ResponseDto.<List<DispositivoAdminDto>>builder()
                        .success(true)
                        .response(result)
                        .build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto<DispositivoAdminDto>> get(@PathVariable String id) {
        DispositivoAdminDto result = dispSer.getById(id);
        return ResponseEntity.ok(
                ResponseDto.<DispositivoAdminDto>builder()
                        .success(true)
                        .response(result)
                        .build());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<DispositivoAdminDto>> add(@RequestBody DispositivoAdminDto dispositivoDto) {
        DispositivoAdminDto result = dispSer.save(dispositivoDto);
        return ResponseEntity.ok(
                ResponseDto.<DispositivoAdminDto>builder()
                        .success(true)
                        .response(result)
                        .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> delete(@PathVariable String id) {
        boolean resp = dispSer.delete(id);
        return ResponseEntity.ok(
            ResponseDto.<String>builder()
                    .success(resp)
                    .response("Eliminado correctamente")
                    .build());
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<ResponseDto<List<DispositivoAdminDto>>> search(@PathVariable String term) {
        List<DispositivoAdminDto> result = dispSer.findByTem(term);
        return ResponseEntity.ok(
                ResponseDto.<List<DispositivoAdminDto>>builder()
                        .success(true)
                        .response(result)
                        .build());
    }
}
