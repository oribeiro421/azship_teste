package tavin.azship.gestaofretes.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tavin.azship.gestaofretes.dto.ExceptionDTO;
import tavin.azship.gestaofretes.handler.exception.ClientNotFoundException;
import tavin.azship.gestaofretes.handler.exception.CodigoNotFoundException;
import tavin.azship.gestaofretes.handler.exception.IdNotFoundException;
import tavin.azship.gestaofretes.handler.exception.PropertiesNotFoundException;

@RestControllerAdvice
public class ExceptionNotFoundHandler {

    @ExceptionHandler(PropertiesNotFoundException.class)
    public ResponseEntity<?> propertiesNotFound(PropertiesNotFoundException exception){
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintNull(ConstraintViolationException exception){
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> propertiesNotFound(IdNotFoundException exception){
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> propertiesNotFound(ClientNotFoundException exception) {
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CodigoNotFoundException.class)
    public ResponseEntity<?> codigoNotFound(CodigoNotFoundException exception){
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}
