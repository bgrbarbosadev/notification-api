package br.com.bgrbarbosa.notification_api.controller.mapper;


import br.com.bgrbarbosa.notification_api.model.Notification;
import br.com.bgrbarbosa.notification_api.model.dto.NotificationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification parseToEntity(NotificationDTO dto);

    NotificationDTO parseToDto(Notification entity);

    List<NotificationDTO> parseToListDTO(List<Notification>list);
}
