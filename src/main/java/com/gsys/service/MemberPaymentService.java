package com.gsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gsys.core.util.NotificationUtil;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Member;
import com.gsys.model.MemberPayment;
import com.gsys.model.Notification;
import com.gsys.repository.MemberPaymentRepository;
import com.gsys.repository.MemberRepository;

@Service
public class MemberPaymentService {
	@Autowired
	private MemberPaymentRepository memberPaymentRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private NotificationService notificationService;

	public ResponseEntity<?> makeMemberPayment(MemberPayment memberPayment, long memberId) throws DataNotFoundException {
		Member memberTemp = memberRepository.getOne(memberId);
		if(memberTemp == null) throw new DataNotFoundException("Member not found.");
		memberPayment.setMember(memberTemp);
		memberTemp.getMemberPayments().add(memberPayment);
		generateMemberNotification(memberTemp.getFirstName());
		memberRepository.save(memberTemp);
		memberPaymentRepository.save(memberPayment);
		return new ResponseEntity<MemberPayment>(memberPayment, HttpStatus.OK);
	}

	public List<MemberPayment> getAllMemberPayments() {
		return this.memberPaymentRepository.findAll();
	}

	public void deleteMemberPayment(long memberPaymentId) {
		this.memberPaymentRepository.deleteById(memberPaymentId);
	}

	public List<MemberPayment> getMemberPaymentHistory(long memberId) {
		return this.memberPaymentRepository.findByMemberId(memberId);
	}
	
	private void generateMemberNotification(String memberName) {
		Notification nf = new Notification();
		nf.setTitle(NotificationUtil.PAYMENT);
		nf.setDescription("Pagamento aluno "+memberName);
		nf.setCategory(NotificationUtil.PAYMENT_CATEGORY);
		notificationService.saveNotification(nf);
	}
	
}
