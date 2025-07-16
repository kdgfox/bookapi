package com.uplus.eureka.book.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.eureka.member.model.dto.Member;

/*
 * @RestController  
 *   RestFul Service를 위한 Conrtoller 
 *   모든 메서드의 응답을  @ResponseBody를 붙여주는 효과
 */
@RestController	

//RestFul에서 서비스할 자원(Domain)명을 붙인다
//@RequestMapping("/요청명")
@RequestMapping("/test")   

/*
 * @CrossOrigin
 *  - CORS 요청에 대한 승인
 *  origins = {"*"}  : 요청하는 모든 URL, 메서드를 승인 
 *     ==> 보안에 취약하므로 상용에서는 사용 안함
 *     ==> 이후에 Configuration을 통해 설정할 예정  
 * */
public class TestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public TestController() {
		logger.debug("TestController 생성됨.");
	}
	
	
	/**
	 * error 메세지 처리시 한글인 경우 깨지므로 한글 처리를 해야한다. 
	 * Content-Type : application/json;charset-UTF-8
	 * @ExceptionHandler
	 * 해당 컨트롤러에서 발생하는 오류를 처리하는 Annotation
	 */
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		logger.error("msg:{}", e.getMessage());
		
		HttpHeaders resheader = new HttpHeaders();
		//에러메세지가 한글인 경우 깨지므로 한글 처리를 위한 응답 헤더 설정
		resheader.add("Content-Type","application/json;charset-UTF-8");
		return new ResponseEntity<String>("처리 중 오류 발생", resheader, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Get방식의 Form Data or Query String을 추출하는 방법
	 * 
	 * 1. 요청 데이타 1개를 추출하기 
	 *  @RequestParam(value='요청 데이타 명', required, defaultValaue) String or Primitive 
	 *  
	 *   value
	 *      - 추출할 요청 데이타 명을 지정
	 *     required
	 *        true : 요청 데이타가 없으면 error 발생 , default
	 *        false: 요청 데이타가 없어도 됨. 
	 *     defaultValue 
	 *        -  요청 데이타가 전달 되지 않는 경우 설정한 기본 값이 전달 된다.  
	 *        
	 * 2. 요청 데이타가 많은 경우 
	 *    2.1 @RequestParam Map
	 *     - request packet에 있는 모든 요청 데이타를 추출해서 Map에 저장해서 전달 된다.           
	 *     
	 *    2.2 DTO  ==> @ModelAttriute를 붙여도 되고 안붙여도 됨. 
	 *   	 - 지정한 DTO의 객체를 생성하고
	 *       객체에 선언된 모든 속성에 대해서 - 속성 이름으로 요청데이타를 추출 후 setter 함수로 객체에 setting
	 *       - 속성에 지정되지 않은 요청 데이타는 무시한다. 
	 *       
	 *  ResponseEntity  
	 *  - 응답 데이타를 담을 객체
	 *  - 리턴 타입으로 선언하면 해당 객체가 JSON으로 변환되어 출력된다.        
	 */
	
//	@GetMapping  //get으로 요청시 
//	public ResponseEntity<String> hello(@RequestParam(value="msg")  String msg) {
//		System.out.println("요청 데이타 :"+ msg);
//		ResponseEntity<String> respose = new ResponseEntity<String>("success", HttpStatus.OK) ;
//		return respose;
//	}
	
//	@GetMapping  
//	public ResponseEntity<Map> hello(@RequestParam Map<String, String> reqData) {
//		System.out.println("요청 데이타 :"+ reqData);
//		ResponseEntity<Map> respose = new ResponseEntity<Map>(reqData, HttpStatus.OK) ;
//		return respose;
//	}
	
	@GetMapping  
	public ResponseEntity<Member> hello(Member member) {
		System.out.println("요청 데이타 :"+ member);
		ResponseEntity<Member> respose = new ResponseEntity<Member>(member, HttpStatus.OK) ;
		return respose;
	}
//	
	


	/**
	 * @PathVariable
	 * 요청 데이타가 url에 있는 경우 path에서 데이타를 전달 받을 때 사용하는 Annotation 
	 * Get 방식에서 범위 데이타를 요청할때와 pk를 이용한 1개의 데이타를 요청할때를 
	 * 구별하기 path로 전달된 pk를 추출할때 사용한다.
	 * 형식]   
	 * @GetMapping("/{데이타명}")
	 * public ResponseEntity  메서드명(@PathVariable("데이타명") Type 인자명){
	 * 
	 * } 
	 */
	@GetMapping("/{isbn}")
	public ResponseEntity<String>  search(@PathVariable("isbn") String isbn){
		logger.debug("search isbn:{}", isbn);

		return new ResponseEntity<String>("success- isbn:"+isbn, HttpStatus.OK);
	}
	
	/**
	 * @RequestBody
	 * 요청 방식이 Put과 Post이면서 요청 데이타가 JSON 형식일 때 
	 * 전달되는 요청 packet의 body를 객체로 전달 받을 때 사용하는 Annotation 
	 */
	@PostMapping
	public ResponseEntity<Member> regist(@RequestBody Member member){
		logger.debug("regist member:{}", member);
		throw new RuntimeException();
		//return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
}



