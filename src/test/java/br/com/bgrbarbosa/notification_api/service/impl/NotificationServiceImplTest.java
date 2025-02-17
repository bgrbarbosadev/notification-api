package br.com.bgrbarbosa.notification_api.service.impl;

import br.com.bgrbarbosa.notification_api.model.Notification;
import br.com.bgrbarbosa.notification_api.model.enums.Status;
import br.com.bgrbarbosa.notification_api.repository.NotificationRepository;
import br.com.bgrbarbosa.notification_api.service.exception.BusinessException;
import br.com.bgrbarbosa.notification_api.service.exception.ResourceNotFound;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    public static final long ID = 1L;
    public static final int INDEX = 0;
    public static final long IDNOTEXIST = 20L;
    @InjectMocks
    private NotificationServiceImpl service;

    @Mock
    private NotificationRepository repository;

    private Notification notification;

    private Notification invalidNotification;


    @BeforeEach
    void setUp() {
        initializeValues();
    }

    @Test
    void mustInsertNotificationSuccessfully() {

        when(repository.save(any())).thenReturn(notification);

        Notification response = service.insertNotification(notification);

        // Verifica se o metodo foi chamado exatamente uma vez
        verify(repository, Mockito.times(1)).save(any());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Notification.class, response.getClass());
        Assertions.assertEquals(notification.getId(), response.getId());
        Assertions.assertEquals(notification.getStatusNotification(),response.getStatusNotification());
        Assertions.assertEquals(notification.getEmail(),response.getEmail());
        Assertions.assertEquals(notification.getMessage(),response.getMessage());
        Assertions.assertEquals(notification.getTel(),response.getTel());
    }

    @Test
    void mustReturnABusinessExceptionWhenTheSchedulingDateInfersTheCurrentDate() {
       try{
            service.insertNotification(invalidNotification);
        } catch(RuntimeException e) {
            Assertions.assertEquals(BusinessException.class, e.getClass());
            Assertions.assertEquals("Schedule date cannot be less than the current date", e.getMessage());
        }

    }

    @Test
    void shouldReturnListOfNotifications() {

        when(repository.findAll()).thenReturn(List.of(notification));

        List<Notification> response = service.findAllNotification();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals(Notification.class, response.get(INDEX).getClass());
        Assertions.assertEquals(ID, response.get(INDEX).getId());
    }

    @Test
    void shouldReturnANotificationWhenSearchingForId() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(notification));

        Notification result = service.searchNotificationById(ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(Notification.class, result.getClass());
        Assertions.assertEquals(notification.getId(), result.getId());
        Assertions.assertEquals(notification.getDateTimeScheduling(), result.getDateTimeScheduling());
        Assertions.assertEquals(notification.getStatusNotification(), result.getStatusNotification());
        Assertions.assertEquals(notification.getTel(), result.getTel());
        Assertions.assertEquals(notification.getEmail(), result.getEmail());
        Assertions.assertEquals(notification.getMessage(), result.getMessage());
        Assertions.assertEquals(notification.getDateTimeModify(), result.getDateTimeModify());
        Assertions.assertEquals(notification.getDateTimeScheduling(), result.getDateTimeScheduling());

    }

    @Test
    void shouldReturnAResourceNotFoundException() {

        when(repository.findById(anyLong())).thenThrow(new ResourceNotFound("Notification not found!!"));

        try{
            service.searchNotificationById(IDNOTEXIST);
        } catch (ResourceNotFound ex) {
            assertEquals(ResourceNotFound.class, ex.getClass());
            assertEquals("Notification not found!!", ex.getMessage());
        }
    }

    @Test
    void runWhenDelete() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(notification));
        doNothing().when(repository).deleteById(anyLong());

        service.deleteNotification(ID);

        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void whenDeletingItShouldReturnObjectNotFoundException() {
        when(repository.findById(anyLong()))
                .thenThrow(new ResourceNotFound("Notification not found!!"));
        try {
            service.deleteNotification(IDNOTEXIST);
        } catch (Exception ex) {
            assertEquals(ResourceNotFound.class, ex.getClass());
            assertEquals("Notification not found!!", ex.getMessage());
        }
    }

    @Test
    void mustChangeTheNotificationStatusToCanceled() {

        when(repository.findById(ID)).thenReturn(Optional.of(notification));
        notification.setStatusNotification(Status.CANCELLED);
        when(repository.save(notification)).thenReturn(notification);

        Notification result = service.cancelNotification(ID);

        Assertions.assertEquals(Notification.class, result.getClass());
        Assertions.assertEquals(notification.getId(), result.getId());
        Assertions.assertEquals(notification.getStatusNotification(), Status.CANCELLED);
    }

    @Test
    void shouldReturnNotificationNotFoundWhenDeleting() {
        when(repository.findById(anyLong()))
                .thenThrow(new ResourceNotFound("Notification not found!!"));
        try {
            service.deleteNotification(IDNOTEXIST);
        } catch (Exception ex) {
            assertEquals(ResourceNotFound.class, ex.getClass());
            assertEquals("Notification not found!!", ex.getMessage());
        }
    }

    private void initializeValues(){

        this.notification = new Notification(
                1L,
                "bgrbarbosa@hotmail.com",
                "24988549631",
                null,
                LocalDateTime.of(2025,02,20,17,22,00),
                null,
                "Olá!! Primeiro teste de notificação",
                Status.SCHEDULED);

        this.invalidNotification = new Notification(
                1L,
                "bgrbarbosa@hotmail.com",
                "24988549631",
                null,
                LocalDateTime.of(2023,05,20,17,22,00),
                null,
                "Olá!! Primeiro teste de notificação",
                Status.SCHEDULED);


    }
}