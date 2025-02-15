package br.com.bgrbarbosa.notification_api.model;


import br.com.bgrbarbosa.notification_api.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "tb_notification")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String tel;

    @Column
    private LocalDateTime dateTimeShipping;

    @Column
    private LocalDateTime dateTimeScheduling;

    @Column
    private LocalDateTime dateTimeModify;

    @Column
    private String message;

    @Column
    @Enumerated(EnumType.STRING)
    private Status statusNotification;

    @PrePersist
    private void prePersist(){
        statusNotification = Status.SCHEDULED;
    }


}
