package br.com.bgrbarbosa.notification_api.service;



import br.com.bgrbarbosa.notification_api.model.Notification;

import java.util.List;


public interface NotificationService {

    Notification insertNotification(Notification notification);

    List<Notification> findAllNotification();

    Notification searchNotificationById(Long id);

    void deleteNotification(Long id);

    Notification cancelNotification(Long id);

}
