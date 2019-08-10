package com.gsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsys.model.Notification;
import com.gsys.service.NotificationService;

@RestController
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping(path = "notifications")
	public List<Notification> retrieveAllNotifications(){
		return notificationService.retrieveAllNotifications();
	}
}
