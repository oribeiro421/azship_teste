package tavin.azship.tentativa.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tavin.azship.tentativa.dto.ExceptionDTO;
import tavin.azship.tentativa.handler.exception.ClientNotFoundException;
import tavin.azship.tentativa.handler.exception.IdNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> idNotFound(IdNotFoundException exception){
        ExceptionDTO exceptionDTO =new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> clientNotFound(ClientNotFoundException exception){
        ExceptionDTO exceptionDTO =new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> threat404(EntityNotFoundException exception){
        ExceptionDTO exceptionDTO =new ExceptionDTO("Usuario ja cadastrado", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> threat404(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO =new ExceptionDTO("Usuario ja cadastrado", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
