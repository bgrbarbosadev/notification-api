package br.com.bgrbarbosa.notification_api.controller.mapper;

import br.com.bgrbarbosa.notification_api.model.Notification;
import br.com.bgrbarbosa.notification_api.model.dto.NotificationDTO;
import br.com.bgrbarbosa.notification_api.model.enums.Status;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-15T22:37:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification parseToEntity(NotificationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setId( dto.id() );
        notification.setEmail( dto.email() );
        notification.setTel( dto.tel() );
        notification.setDateTimeShipping( dto.dateTimeShipping() );
        notification.setDateTimeScheduling( dto.dateTimeScheduling() );
        notification.setDateTimeModify( dto.dateTimeModify() );
        notification.setMessage( dto.message() );
        notification.setStatusNotification( dto.statusNotification() );

        return notification;
    }

    @Override
    public NotificationDTO parseToDto(Notification entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String tel = null;
        LocalDateTime dateTimeShipping = null;
        LocalDateTime dateTimeScheduling = null;
        LocalDateTime dateTimeModify = null;
        String message = null;
        Status statusNotification = null;

        id = entity.getId();
        email = entity.getEmail();
        tel = entity.getTel();
        dateTimeShipping = entity.getDateTimeShipping();
        dateTimeScheduling = entity.getDateTimeScheduling();
        dateTimeModify = entity.getDateTimeModify();
        message = entity.getMessage();
        statusNotification = entity.getStatusNotification();

        NotificationDTO notificationDTO = new NotificationDTO( id, email, tel, dateTimeShipping, dateTimeScheduling, dateTimeModify, message, statusNotification );

        return notificationDTO;
    }
}
