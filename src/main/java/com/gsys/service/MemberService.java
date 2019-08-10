package com.gsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gsys.core.util.NotificationUtil;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Member;
import com.gsys.model.Notification;
import com.gsys.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private NotificationService notificationService;
	

	public List<Member> getAllMembers() {
		return this.memberRepository.findAll();
	}

	public Member getMember(long memberId) throws DataNotFoundException {
		Member member = this.memberRepository.getOne(memberId);
		if (member == null) {
			throw new DataNotFoundException("Member not found: " + memberId);
		}
		return member;
	}

	public ResponseEntity<?> createMember(Member member) {
		Member createdMember = this.memberRepository.save(member);
		generateMemberNotification(member.getFirstName());
		return new ResponseEntity<Member>(createdMember, HttpStatus.OK);
	}

	public void deleteMember(long memberId) {
		this.memberRepository.deleteById(memberId);
		
	}

	public List<Member> getNonPayingMembers() {
		return memberRepository.findNonPayingMembers();
	}

	public List<Member> getPayingMembers() {
		return memberRepository.findPayingMembers();
	}

	public void editMember(Member member) {
		memberRepository.save(member);
	}
	
	private void generateMemberNotification(String memberName) {
		Notification nf = new Notification();
		nf.setTitle(NotificationUtil.MEMBER_REGISTRATION);
		nf.setDescription("Aluno "+memberName+" cadastrado no sistema");
		nf.setCategory(NotificationUtil.REGISTRATION_CATEGORY);
		notificationService.saveNotification(nf);
	}
	
	

}
