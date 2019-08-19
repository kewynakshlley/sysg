package com.gsys.model;




import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MEMBER")
public class Member extends Person {
	@Column
	private int paymentDate;
	@OneToMany(mappedBy="member")
	private List<MemberPayment> memberPayments;

	public Member() {
	}

	public Member(long id, String name, String address, String cpf, String photo, String dateOfBirth, 
			String gender, List<MemberPayment> memberPayments,  String firstName, String lastName, int paymentDate) {
		super(id, name, address, cpf, photo, dateOfBirth, firstName, lastName);
		this.memberPayments = memberPayments;
		this.paymentDate = paymentDate;
	}

	public List<MemberPayment> getMemberPayments() {
		return memberPayments;
	}

	public void setMemberPayments(List<MemberPayment> memberPayments) {
		this.memberPayments = memberPayments;
	}

	public int getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(int paymentDate) {
		this.paymentDate = paymentDate;
	}

	
	

}
