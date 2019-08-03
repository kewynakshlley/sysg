package com.gsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.gsys.model.MemberPayment;
import com.gsys.service.MemberPaymentService;

@RestController
public class MemberPaymentController {
	
	@Autowired
	private MemberPaymentService memberPaymentService;
	

	@GetMapping(path = "/member-payment-service")
	public List<MemberPayment> getAllMemberPaymentService() {
		return this.memberPaymentService.getAllMemberPayments();
	}

	@DeleteMapping(path = "/member-payment-service/{memberPaymentId}")
	public void deleteMemberPayment(@PathVariable long memberPaymentId) {
		memberPaymentService.deleteMemberPayment(memberPaymentId);
	}
}
