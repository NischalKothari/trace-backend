package trace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(
            MethodArgumentNotValidException ex
    ){

        Map<String,String> errors = new HashMap<>();
        for(FieldError err : ex.getBindingResult().getFieldErrors()){
            errors.put(err.getField(),err.getDefaultMessage());
        }
        Map<String,Object> response = new HashMap<>();

        response.put("status", 400);
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(
            ResourceNotFoundException ex
    ){

        Map<String,Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
