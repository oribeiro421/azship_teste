package tavin.azship.gestaofretes.api.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tavin.azship.gestaofretes.api.dto.ExceptionDTO;
import tavin.azship.gestaofretes.domain.exception.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                    .message(Collections.singletonList(ex.getMessage()))
                    .statusCode(statusCode.value())
                    .build();
        } else if (body instanceof String) {
            body = ExceptionDTO.builder()
                    .message(Collections.singletonList((String) body))
                    .statusCode(statusCode.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                     HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return fieldName + ": " + errorMessage;
                })
                .collect(Collectors.toList());

        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .message(errorMessages)
                .statusCode(status.value())
                .build();

        return super.handleExceptionInternal(ex, exceptionDTO, headers, status, request);
    }
}
