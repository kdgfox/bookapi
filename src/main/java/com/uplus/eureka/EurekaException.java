package com.uplus.eureka;

public class EurekaException extends RuntimeException {
	public EurekaException(String msg) {
		super(msg); //부모 생성자 호출 
	}
}
