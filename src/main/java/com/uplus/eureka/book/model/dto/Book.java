package com.uplus.eureka.book.model.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Book implements Serializable {
	private static final long serialVersionUID=1L;
	@Schema(description = "책 일련 번호" , example = "979-11-602")
	private String isbn         ;
	@Schema(description = "제목" , example="스프링 부트 3 백엔드 개발자 되기2" )
	private String title        ;
	@Schema(description = "저자" , example="동글양" )
	private String author       ;
	@Schema(description = "가격" , example="42000" )
	private int 	price       ;
	@Schema(description = "책 상세 설명" , example="자바 백엔드 개발자가 되고 싶다면" )
	private String describ      ;
	@Schema(description = "이미지" , example="979-11-602.png" )
	private String img          ;	
}
