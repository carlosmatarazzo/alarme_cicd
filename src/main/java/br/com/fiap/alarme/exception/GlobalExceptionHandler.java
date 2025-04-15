package br.com.fiap.alarme.exception;

import br.com.fiap.alarme.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handler for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        ApiError apiError = new ApiError("Validation error");
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                apiError.addError(fieldError.getField() + ": " + fieldError.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(globalError ->
                apiError.addError(globalError.getObjectName() + ": " + globalError.getDefaultMessage()));

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // Handler for not found exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError("Error processing request");
        apiError.addError(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
