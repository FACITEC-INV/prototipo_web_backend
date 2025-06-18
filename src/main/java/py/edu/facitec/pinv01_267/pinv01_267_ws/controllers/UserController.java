package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.UserDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.UserService;

@RestController
@RequestMapping("/api/admin/users")
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
}
