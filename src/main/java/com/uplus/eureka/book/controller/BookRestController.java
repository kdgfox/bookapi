package com.uplus.eureka.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.eureka.EurekaException;
import com.uplus.eureka.book.model.dto.Book;
import com.uplus.eureka.book.model.dto.PageBean;
import com.uplus.eureka.book.model.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController	
@RequestMapping("/book")
@Tag(name = "책 Rest 컨트롤러", description = "책 목록과 상세보기, 등록, 수정, 삭제등 전반적인 책 정보 관리를 처리하는 클래스")
@AllArgsConstructor
@Slf4j
public class BookRestController {
	private static final String SUCCESS="SUCCESS";
	
	private BookService service;

	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		log.error("msg:{}", e.getMessage());
		
		HttpHeaders resheader = new HttpHeaders();
		//에러메세지가 한글인 경우 깨지므로 한글 처리를 위한 응답 헤더 설정
		resheader.add("Content-Type","application/json;charset-UTF-8");
		
		String msg ="처리 중 오류 발생";
		if (e instanceof EurekaException) {
			msg = e.getMessage();
		}		
		return new ResponseEntity<String>(msg, resheader, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Operation(	summary = "책 상세 정보", 
			description = "일련 번호에 대한 책의 상세 정보를 반환해 줍니다. ",
			responses ={
					@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
					@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
					@ApiResponse(responseCode = "500", description = "서버에러!!")
			}
		  )
	@GetMapping("/{isbn}")
	public ResponseEntity<Book> search(@PathVariable("isbn") String isbn){
		log.debug("isbn:{}", isbn);
		Book book = service.search(isbn);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@Operation(	summary = "책목록", 
				description = "책에 대한 <big>전체 목록</big>을 반환해 줍니다.",
				responses ={
						@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
						@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
						@ApiResponse(responseCode = "500", description = "서버에러!!")
				}
			  )
	@GetMapping
	public ResponseEntity<?> searchAll(@Schema(implementation = PageBean.class) PageBean bean){
		log.debug("bean:{}", bean);
		List<Book> books = service.searchAll(bean);
		log.debug("books:{}", books);
		
		if(books!=null && books.size()>0) {
			Map<String, Object> result = new HashMap<>();
			result.put("books", books);
			result.put("page", bean);
			return new ResponseEntity<Map>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@Operation(	summary = "책 정보 등록", 
			description = "",
			responses ={
					@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
					@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
					@ApiResponse(responseCode = "500", description = "서버에러!!")
			}
		  )
	@PostMapping
	public ResponseEntity<String> regist(@RequestBody @Schema(implementation = Book.class) Book book){
		log.debug("regist- book:{}",book);
		service.insert(book);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@Operation(	summary = "책목록", 
			description = "책에 대한 <big>전체 목록</big>을 반환해 줍니다.",
			responses ={
					@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
					@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
					@ApiResponse(responseCode = "500", description = "서버에러!!")
			}
		  )
	@PutMapping
	public ResponseEntity<String> update(@RequestBody @Schema(implementation = Book.class) Book book){
		log.debug("update- book:{}",book);
		service.update(book);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@Operation(	summary = "책목록", 
			description = "책에 대한 <big>전체 목록</big>을 반환해 줍니다.",
			responses ={
					@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
					@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
					@ApiResponse(responseCode = "500", description = "서버에러!!")
			},
			parameters= {
					@Parameter(name="isbn", description = "", required = true, example="")
			}
	)	
	@DeleteMapping("/{isbn}")
	public ResponseEntity<String> remove(@PathVariable("isbn") String isbn){
		log.debug("remove- isbn:{}",isbn);
		service.remove(isbn);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}






