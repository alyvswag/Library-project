package com.example.libraryproject.model.dao.entity;

import com.example.libraryproject.model.dao.Base;
import com.example.libraryproject.model.enums.notification.DataType;
import com.example.libraryproject.model.enums.notification.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification extends Base {
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @Column(name = "data_type")
    @Enumerated(EnumType.STRING)
    DataType dataType;
    @Column(name = "message")
    String message;
    @Column(name = "notification_status")
    @Enumerated(EnumType.STRING)
    NotificationStatus notificationStatus;
}
