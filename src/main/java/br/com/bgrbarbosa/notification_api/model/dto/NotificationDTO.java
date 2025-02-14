package br.com.bgrbarbosa.notification_api.model.dto;


import br.com.bgrbarbosa.notification_api.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public record NotificationDTO(
        Long id,
        String email,
        String tel,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dateTimeShipping,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dateTimeScheduling,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dateTimeModify,
        String message,
        Status statusNotification
) {
}
