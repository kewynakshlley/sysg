package com.gsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gsys.exception.DataAlreadyExistsException;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Member;
import com.gsys.model.MemberPayment;
import com.gsys.service.MemberPaymentService;
import com.gsys.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberPaymentService memberPaymentService;

	@GetMapping(path = "/members")
	public List<Member> getAllMembers() {
		return this.memberService.getAllMembers();
	}

	@GetMapping(path = "/members/{memberId}")
	public Member getMember(@PathVariable long memberId) throws DataNotFoundException {
		return this.memberService.getMember(memberId);
	}

	@PostMapping(path = "/members/new")
	public ResponseEntity<?> createMember(@RequestBody Member member) throws DataAlreadyExistsException {
		return this.memberService.createMember(member);
	}

	@DeleteMapping(path = "/members/{memberId}")
	public void deleteMember(@PathVariable long memberId) {
		memberService.deleteMember(memberId);
	}

	@GetMapping(path = "/members/{memberId}/check-additions")
	public void checkAdditions() {

	}

	@PostMapping(path = "/members/{memberId}/make-payment")
	public ResponseEntity<?> makeMemberPayment(@RequestBody MemberPayment memberPayment, @PathVariable long memberId)
			throws DataNotFoundException {
		return this.memberPaymentService.makeMemberPayment(memberPayment, memberId);
	}

	@GetMapping(path = "members/{memberId}/payment-history")
	public List<MemberPayment> getMemberPaymentHistory(@PathVariable long memberId) {
		return this.memberPaymentService.getMemberPaymentHistory(memberId);

	}

	@GetMapping(path = "members/non-paying-members")
	public List<Member> getNonPayingMembers() {
		return memberService.getNonPayingMembers();
	}

	@GetMapping(path = "members/paying-members")
	public List<Member> getPayingMembers() {
		return memberService.getPayingMembers();
	}
	
	@PutMapping(path = "members/edit-member")
	public void editMember(@RequestBody Member member) {
		memberService.editMember(member);
	}
	
	

}
