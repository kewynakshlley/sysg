package com.gsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsys.model.Notification;
import com.gsys.repository.NotificationRepository;

@Service
public class NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;

	public List<Notification> retrieveAllNotifications() {
		return notificationRepository.findAll();
	}
	
	public void saveNotification(Notification nfc) {
		notificationRepository.save(nfc);
	}

}
