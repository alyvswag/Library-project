package com.example.libraryproject.repository.notification;

import com.example.libraryproject.model.dao.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification, Long> {

    @Query("Select n from Notification n where n.user.id = :userId  and n.notificationStatus != 'DELETED' ")
    List<Notification> findNotificationsByUserId(@Param("userId")Long userId);


    @Query("Select n from Notification  n where n.id = :notificationId and n.notificationStatus != 'DELETED' ")
    Optional<Notification> findNotificationById(@Param("notificationId")Long notificationId);
}
