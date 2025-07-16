package com.uplus.eureka.book.model.dto;

import com.uplus.eureka.EurekaException;

public class BookException extends EurekaException {
	public BookException(String msg) {
		super(msg);
	}
}
