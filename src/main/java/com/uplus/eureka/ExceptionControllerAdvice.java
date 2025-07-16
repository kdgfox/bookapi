package com.uplus.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.eureka.book.controller.BookRestController;
import com.uplus.eureka.book.controller.TestController;
import com.uplus.eureka.member.controller.MemberController;

/**
 * @ControllerAdvice
 * Project에서 발생하는 모든 오류를 처리하는 기능 
 */
//@ControllerAdvice(annotations = {RestController.class}, basePackageClasses = {BookRestController.class,TestController.class, MemberController.class})
public class ExceptionControllerAdvice {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		logger.error("msg:{}", e.getMessage());
		
		HttpHeaders resheader = new HttpHeaders();
		//에러메세지가 한글인 경우 깨지므로 한글 처리를 위한 응답 헤더 설정
		resheader.add("Content-Type","application/json;charset-UTF-8");
		
		String msg ="처리 중 오류 발생";
		if (e instanceof EurekaException) {
			msg = e.getMessage();
		}		
		return new ResponseEntity<String>(msg, resheader, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
