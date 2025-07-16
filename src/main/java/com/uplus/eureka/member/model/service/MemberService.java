package com.uplus.eureka.member.model.service;

import com.uplus.eureka.member.model.dto.Member;

public interface MemberService {
	Member login(String id, String pass);
	Member search(String id) ;
	void regist(Member user) ;
	void update(Member user) ;
	void remove(String id)   ;
}
