package br.com.bgrbarbosa.notification_api.controller.exception;

import br.com.bgrbarbosa.notification_api.service.exception.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFound e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setPath(request.getRequestURI());
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Notification not found!!");
        return ResponseEntity.status(status).body(err);
    }
}
