package br.com.bgrbarbosa.notification_api.controller.exception;

import br.com.bgrbarbosa.notification_api.controller.exception.dto.ErrorResponse;
import br.com.bgrbarbosa.notification_api.service.exception.ResourceNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.FieldError;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GlobalExceptionHandleTest {

    public static final String ENTITY_NOT_FOUND = "Notification not found!!";

    @InjectMocks
    private GlobalExceptionHandle globalExceptionHandle;

    @Test
    void mustReturnAnEntityNotFoundException() {

        ResponseEntity<StandardError> response = globalExceptionHandle.entityNotFound(
                new ResourceNotFound(ENTITY_NOT_FOUND), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(ENTITY_NOT_FOUND, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/notification/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void shouldReturnAnMethodArgumentNotValidException() {


    }
}