package br.com.bgrbarbosa.notification_api.controller.exception;

import br.com.bgrbarbosa.notification_api.controller.exception.dto.ErrorResponse;
import br.com.bgrbarbosa.notification_api.service.exception.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.bgrbarbosa.notification_api.controller.exception.dto.Error;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFound e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setPath(request.getRequestURI());
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Notification not found!!");
        log.error("Notification not found! - " + "Path: " + err.getPath());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        log.error("Validation error: {} ", e.getMessage());

        // A variável fieldErros armazena todos os erros que vieram na exception MethodArgumentNotValidException
        List<FieldError> fieldErrors = e.getFieldErrors();

        // Rotina que converte o erro que veio na exception, no nosso erro personalizado e armazena em uma lista a ser retornada no método
        List<Error> listErros = fieldErrors
                .stream()
                .map(ev -> new Error(ev.getField(), ev.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação.",
                listErros);
    }
}
