package com.uplus.eureka;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.uplus.eureka.book.model.dao.BookDao;
import com.uplus.eureka.book.model.dto.Book;
import com.uplus.eureka.book.model.service.BookService;
import com.uplus.eureka.member.model.dao.MemberDao;
import com.uplus.eureka.member.model.dto.Member;


@SpringBootTest(
		properties = {"spring.config.location=classpath:application.properties" }
)
@ComponentScan(basePackages = {"com.uplus.eureka"})
class SpringBootTestApplicationBookTests {
	private Logger log  = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private BookDao  dao;
	
	@Autowired
	private BookService service;
	
	@Test
	public void serviceTest() {
		//null 인지 체크하는 단정 함수 
		assertNotNull(service);
	}
	
	@Test
	public void dsTest() {
		//null 인지 체크하는 단정 함수 
		assertNotNull(ds);
	}
	
	@Test
	public void daoTest() {
		assertNotNull(ds);
	}
	
	@Test
	public void searchTest() throws SQLException {
		Book book =  dao.search("979-11");
		assertNotNull(book);
		log.debug("book:{}", book);
	}
}
