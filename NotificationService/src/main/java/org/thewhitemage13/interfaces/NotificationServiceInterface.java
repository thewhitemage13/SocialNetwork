package org.thewhitemage13.interfaces;

import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.dto.GetNotificationDTO;
import org.thewhitemage13.exception.NotificationNotFoundException;

import java.util.List;

public interface NotificationServiceInterface {
    void createNotification(CreateNotificationDTO createNotificationDTO);
    void updateStatus(Long notificationId, boolean status) throws NotificationNotFoundException;
    List<GetNotificationDTO> getNotificationsByUserId(Long userId) throws NotificationNotFoundException;
    void deleteAllByUserId(Long userId);
    GetNotificationDTO getNotificationById(Long notificationId) throws NotificationNotFoundException;
}
