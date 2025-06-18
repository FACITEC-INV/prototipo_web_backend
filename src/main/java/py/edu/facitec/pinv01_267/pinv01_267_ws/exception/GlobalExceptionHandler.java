package py.edu.facitec.pinv01_267.pinv01_267_ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ResponseDto<String>> handleEntityNotFound(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        ResponseDto.<String>builder()
            .success(false)
            .response(ex.getMessage())
            .build()
    );
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ResponseDto<String>> handleBadRequest(BadRequestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        ResponseDto.<String>builder()
            .success(false)
            .response(ex.getMessage())
            .build()
    );
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ResponseDto<String>> handleBadRequest(BadCredentialsException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
        ResponseDto.<String>builder()
            .success(false)
            .response("Credenciales incorrectas")
            .build()
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDto<String>> handleGeneric(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        ResponseDto.<String>builder()
            .success(false)
            .response("Error interno: " + ex.getMessage())
            .build()
    );
  }
}
