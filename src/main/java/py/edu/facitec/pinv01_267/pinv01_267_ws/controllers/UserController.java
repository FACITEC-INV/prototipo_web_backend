package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.UserDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.UserService;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ROOT')")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<UserDto>> save(@RequestBody UserDto dto) {
        UserDto savedUser = userService.save(dto);
        return ResponseEntity.ok(
            ResponseDto.
                <UserDto>builder()
                .success(true)
                .response(savedUser).build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto<UserDto>> get(@PathVariable Long id) {
        UserDto result = userService.getById(id);
        return ResponseEntity.ok(
                ResponseDto.<UserDto>builder()
                        .success(true)
                        .response(result)
                        .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<UserDto>>> getAll() {
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok(
            ResponseDto.
                <List<UserDto>>builder()
                .success(true)
                .response(users).build()
        );
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> delete(@PathVariable Long id) {
        boolean resp = userService.delete(id);
        return ResponseEntity.ok(
            ResponseDto.<String>builder()
                    .success(resp)
                    .response("Eliminado correctamente")
                    .build());
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<ResponseDto<List<UserDto>>> search(@PathVariable String term) {
        List<UserDto> result = userService.findByTem(term);
        return ResponseEntity.ok(
                ResponseDto.<List<UserDto>>builder()
                        .success(true)
                        .response(result)
                        .build());
    }

}
