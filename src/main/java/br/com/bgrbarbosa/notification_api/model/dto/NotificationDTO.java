package br.com.bgrbarbosa.notification_api.model.dto;


import br.com.bgrbarbosa.notification_api.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record NotificationDTO(

        Long id,

        @Email(message = "Invalid email!")
        String email,

        @NotBlank(message = "Phone field cannot be empty")
        String tel,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dateTimeShipping,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @NotNull(message = "Enter a date")
        LocalDateTime dateTimeScheduling,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dateTimeModify,

        @NotEmpty(message = "Message must be completed")
        String message,

        Status statusNotification
) {
}
