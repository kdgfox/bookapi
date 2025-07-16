package com.uplus.eureka.member.model.dto;

import com.uplus.eureka.EurekaException;

public class MemberException extends EurekaException {
	public MemberException(String msg) {
		super(msg);
	}
}
