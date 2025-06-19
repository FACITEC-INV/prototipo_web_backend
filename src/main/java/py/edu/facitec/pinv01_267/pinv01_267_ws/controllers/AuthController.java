package py.edu.facitec.pinv01_267.pinv01_267_ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.AuthRequestDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.UserDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.sevices.UserService;
import py.edu.facitec.pinv01_267.pinv01_267_ws.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired 
    private AuthenticationManager authManager;

    @Autowired 
    private UserService userService;

    @Autowired 
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<String>> login(@RequestBody AuthRequestDto authRequest) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
            authRequest.getUsername(), authRequest.getPassword()
        ));

        UserDto user = userService.findByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(
            ResponseDto.<String>builder()
                .success(true)
                .response(token).build()
        );
    }
}