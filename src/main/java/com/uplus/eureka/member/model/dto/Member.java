package com.uplus.eureka.member.model.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Member implements Serializable {
	private static final long serialVersionUID=1L;
	@Schema(description = "아이디" 	, example = "eurekauplus")
	private String id;
	@Schema(description = "비밀번호" 	, example = "1111")
	private String password;
	@Schema(description = "이름" 	, example = "유레카")
	private String name;
	@Schema(description = "이메일" 	, example = "eurekauplus@eureka.com")
	private String email;
	@Schema(description = "주소" 	, example = "서울시")
	private String address;
	@Schema(description = "전화번호" 	, example = "010")
	private String phone;
	@Schema(description = "탈퇴 여부" , example = "N")
	private String withdraw;
}
