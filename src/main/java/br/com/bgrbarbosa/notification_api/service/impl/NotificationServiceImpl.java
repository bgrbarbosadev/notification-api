package br.com.bgrbarbosa.notification_api.service.impl;


import br.com.bgrbarbosa.notification_api.model.Notification;
import br.com.bgrbarbosa.notification_api.model.enums.Status;
import br.com.bgrbarbosa.notification_api.repository.NotificationRepository;
import br.com.bgrbarbosa.notification_api.service.NotificationService;
import br.com.bgrbarbosa.notification_api.service.exception.BusinessException;
import br.com.bgrbarbosa.notification_api.service.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository repository;

    @Override
    public Notification insertNotification(Notification notification) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime dateScheduling = notification.getDateTimeScheduling();

        if (dateScheduling.isBefore(currentDate)) {
            log.error("Date cannot be less than the current date");
            throw new BusinessException("Schedule date cannot be less than the current date");
        }

        return repository.save(notification);
    }

    @Override
    public List<Notification> findAllNotification() {
        return repository.findAll();
    }

    @Override
    public Notification searchNotificationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Notification not found!!"));
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Notification not found!!"));
        repository.deleteById(id);
    }

    @Override
    public Notification cancelNotification(Long id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Notification not found!!"));
        notification.setStatusNotification(Status.CANCELLED);
        return repository.save(notification);
    }
}
