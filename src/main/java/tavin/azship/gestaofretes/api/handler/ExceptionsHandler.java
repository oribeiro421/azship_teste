package tavin.azship.gestaofretes.api.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tavin.azship.gestaofretes.api.dto.ExceptionDTO;
import tavin.azship.gestaofretes.domain.exception.*;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException exception, WebRequest request){
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ResourceEmptyException.class, ConstraintViolationException.class,
            InvalidDataAccessApiUsageException.class, NullPointerException.class})
    public ResponseEntity<?> resourceEmptyHandler(RuntimeException exception, WebRequest request){

        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                     HttpStatusCode statusCode, WebRequest request) {

        if (body == null) {
            body = ExceptionDTO.builder()
                    .message(ex.getMessage())
                    .statusCode(statusCode.value())
                    .build();
        } else if (body instanceof String) {
            body = ExceptionDTO.builder()
                    .message((String) body)
                    .statusCode(statusCode.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                     HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .message(errorMessage)
                .statusCode(status.value())
                .build();

        return new ResponseEntity<>(exceptionDTO, headers, status);
    }
}
