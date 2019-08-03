package com.gsys.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MEMBER")
public class Member extends Person {
	@OneToMany(mappedBy="member")
	private List<MemberPayment> memberPayments;

	public Member() {
	}

	public Member(long id, String name, String address, String cpf, String photo, String dateOfBirth, 
			String gender, List<MemberPayment> memberPayments) {
		super(id, name, address, cpf, photo, dateOfBirth, gender);
		this.memberPayments = memberPayments;
	}

	public List<MemberPayment> getMemberPayments() {
		return memberPayments;
	}

	public void setMemberPayments(List<MemberPayment> memberPayments) {
		this.memberPayments = memberPayments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((memberPayments == null) ? 0 : memberPayments.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (memberPayments == null) {
			if (other.memberPayments != null)
				return false;
		} else if (!memberPayments.equals(other.memberPayments))
			return false;
		return true;
	}
	
	
	
	
	
	

}
