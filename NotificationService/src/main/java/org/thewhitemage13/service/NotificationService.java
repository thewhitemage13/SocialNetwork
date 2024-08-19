package org.thewhitemage13.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.dto.GetNotificationDTO;
import org.thewhitemage13.entity.Notification;
import org.thewhitemage13.exception.NotificationNotFoundException;
import org.thewhitemage13.interfaces.NotificationServiceInterface;
import org.thewhitemage13.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationService implements NotificationServiceInterface {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        notificationRepository.deleteAllByUserId(userId);
    }

    @Override
    public void createNotification(CreateNotificationDTO createNotificationDTO) {
        Notification notification = new Notification();

        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setType(createNotificationDTO.getType());
        notification.setMessage(createNotificationDTO.getMessage());
        notification.setUserId(createNotificationDTO.getUserId());

        notificationRepository.save(notification);
    }

    @Override
    public void updateStatus(Long notificationId, boolean status) throws NotificationNotFoundException {
        Notification update = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification with id = %s not found".formatted(notificationId)));
        update.setRead(status);
        notificationRepository.save(update);
    }

    @Override
    public List<GetNotificationDTO> getNotificationsByUserId(Long userId) throws NotificationNotFoundException {
        List<Notification> notifications =
                notificationRepository.findAllByUserId(userId)
                        .orElseThrow(() -> new NotificationNotFoundException("Notifications for user with id = %s is not found".formatted(userId)));

        List<GetNotificationDTO> dtos = new ArrayList<>();
        for (Notification notification : notifications) {
            GetNotificationDTO dto = new GetNotificationDTO();
            dto.setCreatedAt(notification.getCreatedAt());
            dto.setRead(true);
            dto.setType(notification.getType());
            dto.setMessage(notification.getMessage());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public GetNotificationDTO getNotificationById(Long notificationId) throws NotificationNotFoundException {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification with id = %s not found".formatted(notificationId)));

        GetNotificationDTO dto = new GetNotificationDTO();
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setRead(true);
        dto.setType(notification.getType());
        dto.setMessage(notification.getMessage());
        return dto;
    }
}
