package br.com.bgrbarbosa.notification_api.model.dto;


import br.com.bgrbarbosa.notification_api.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;


public record NotificationDTO(

        Long id,

        @Email(message = "{email.message}")
        @NotBlank(message = "{not.blank.message}")
        String email,

        @NotBlank(message = "{not.blank.message}")
        String tel,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dateTimeShipping,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @NotNull(message = "{not.null.message}")
        LocalDateTime dateTimeScheduling,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dateTimeModify,

        @NotEmpty(message = "{not.empty.message}")
        @Size(min = 10, max = 100, message = "{size.message}")
        String message,

        Status statusNotification) {
}
