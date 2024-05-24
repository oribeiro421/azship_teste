package tavin.azship.gestaofretes.api.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tavin.azship.gestaofretes.api.dto.ExceptionDTO;
import tavin.azship.gestaofretes.domain.exception.*;
import tavin.azship.gestaofretes.handler.exception.*;

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
    @ExceptionHandler(AddressCollectNotFoundException.class)
    public ResponseEntity<?> addressCollectNotFound(AddressCollectNotFoundException exception) {
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AddressDeliveryNotFoundException.class)
    public ResponseEntity<?> addressDeliveryNotFound(AddressDeliveryNotFoundException exception) {
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}
