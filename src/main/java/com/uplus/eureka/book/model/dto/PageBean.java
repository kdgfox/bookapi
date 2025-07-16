package com.uplus.eureka.book.model.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** UI 화면 페이지에 대한 정보를 표시하는 클래스  */
@Data
public class PageBean implements Serializable{
	@Schema(description = "검색 조건" , example = "title")
	private String key;
	@Schema(description = "검색 단어" , example = "자")
	private String word;
	/**페이징 처리에 대한 link정보*/
	private String pageLink;
	/**현재 페이지 번호*/
	@Schema(description = "페이지 번호" , example = "1")
	private int pageNo;
	/**한 페이지에 보여주 content 개수*/
	@Schema(description = "한페이지에 보여줄 데이타 개수" , example = "3")
	private int interval = 20;
	/**페이지 시작 번호*/
	private int start=0;
}












