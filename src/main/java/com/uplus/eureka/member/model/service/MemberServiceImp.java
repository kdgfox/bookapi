package com.uplus.eureka.member.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.uplus.eureka.member.model.dao.MemberDao;
import com.uplus.eureka.member.model.dto.Member;
import com.uplus.eureka.member.model.dto.MemberException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImp implements MemberService {
	
	private MemberDao dao ;
	public Member login(String id, String pass) {
		try {
			Member user = dao.search(id);
			System.out.println(user);
			if(user == null) throw new MemberException("등록되지 않은 아이디입니다.");
			
			if(!pass.equals(user.getPassword()))
				throw new MemberException("비밀 번호 오류 발생!!!!");
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("로그인 처리 중 오류 발생");
		}
	}
	@Override
	public Member search(String id) {
		try {
			Member member = dao.search(id);
			if(member == null) throw new MemberException("등록되지 않은 아이디입니다.");
			if(member.getWithdraw().equals("Y"))
				throw new MemberException("이미 탈퇴한 아이디입니다.");
			return member;
		} catch (SQLException e) {
			throw new MemberException("회원 정보 조회 중 오류 발생");
		}
	}

	@Override
	public void regist(Member user) {
		try {
			Member find = dao.search(user.getId());
			if(find!=null) {
				throw new MemberException("이미 등록된 아이디 입니다.");
			}
			dao.regist(user);
		} catch (SQLException e) {
			throw new MemberException("회원 정보 처리 중 오류 발생!!!");
		}
	}

	@Override
	public void update(Member user) {
		try {
			Member find = dao.search(user.getId());
			if(find== null) throw new MemberException("등록되지 않은 회원정보입니다. 수정할 수 없습니다.");
			
			dao.update(user);
		} catch (SQLException e) {
			throw new MemberException("회원 정보 수정 중 오류 발생");
		}
	}

	@Override
	public void remove(String id) {
		try {
			Member member = dao.search(id);
			if(member == null) throw new MemberException("등록되지 않은 회원 정보입니다. 삭제할 수 없습니다.");
			dao.remove(id);
		} catch (SQLException e) {
			throw new MemberException("회원 정보 삭제 중 오류 발생");
		}
	}
}




