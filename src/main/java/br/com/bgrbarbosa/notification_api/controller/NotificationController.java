package br.com.bgrbarbosa.notification_api.controller;


import br.com.bgrbarbosa.notification_api.controller.mapper.NotificationMapper;
import br.com.bgrbarbosa.notification_api.model.Notification;
import br.com.bgrbarbosa.notification_api.model.dto.NotificationDTO;
import br.com.bgrbarbosa.notification_api.service.impl.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServiceImpl service;

    private final NotificationMapper mapper;

    @PostMapping
    public ResponseEntity<NotificationDTO> insert(@RequestBody NotificationDTO notification){
        Notification result = service.insertNotification(mapper.parseToEntity(notification));
        return ResponseEntity.ok(mapper.parseToDto(result));
    }

    @GetMapping
    public ResponseEntity<List<Notification>> search(){
        return ResponseEntity.ok(service.findAllNotification());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> searchById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.searchNotificationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.deleteNotification(id);
        return ResponseEntity.accepted().body("Successfully deleted!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> cancel(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.cancelNotification(id));
    }

}
