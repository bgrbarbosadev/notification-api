package br.com.bgrbarbosa.notification_api.repository;


import br.com.bgrbarbosa.notification_api.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
