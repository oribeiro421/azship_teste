package tavin.azship.gestaofretes.api.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tavin.azship.gestaofretes.api.dto.ExceptionDTO;
import tavin.azship.gestaofretes.domain.exception.*;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException exception){
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceEmptyException.class, ConstraintViolationException.class})
    public ResponseEntity<?> resourceEmptyHandler(ResourceEmptyException exception){
        ExceptionDTO ex = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

}
