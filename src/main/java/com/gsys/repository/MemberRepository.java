package com.gsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gsys.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	@Query(value = "SELECT * FROM member WHERE id IN "
			+ "(SELECT member_id FROM member_payment "
			+ "WHERE (CURDATE() - 30) > member_payment_date AND id IN "
			+ "(SELECT max(id) FROM member_payment "
			+ "GROUP BY member_id))", nativeQuery = true)
	List<Member> findNonPayingMembers();
	@Query(value = "SELECT * FROM member WHERE id NOT IN "
			+ "(SELECT member_id FROM member_payment "
			+ "WHERE (CURDATE() - 30) > member_payment_date AND id IN "
			+ "(SELECT max(id) FROM member_payment "
			+ "GROUP BY member_id))", nativeQuery = true)
	List<Member> findPayingMembers();

}
