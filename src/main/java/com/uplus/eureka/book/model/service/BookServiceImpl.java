package com.uplus.eureka.book.model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.uplus.eureka.EurekaException;
import com.uplus.eureka.book.model.dao.BookDao;
import com.uplus.eureka.book.model.dto.Book;
import com.uplus.eureka.book.model.dto.BookException;
import com.uplus.eureka.book.model.dto.PageBean;
import com.uplus.eureka.util.PageUtility;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BookServiceImpl implements BookService {
	private BookDao dao;
	@Override
	public void insert(Book book) {
		try {
			Book find = dao.search(book.getIsbn());
			if(find!=null)
				throw new BookException("이미 등록된 isbn입니다.");
			
			dao.insert(book);
		} catch (SQLException e) {
			throw new BookException("책 정보 등록 중 오류 발생");
		}
	}

	@Override
	public void update(Book book) {
		try {
			Book find = dao.search(book.getIsbn());
			if(find == null) throw new BookException("등록되지 않은 책 정보를 수정할 수 없습니다.");
			dao.update(book);
		} catch (SQLException e) {
			throw new BookException("책 정보 수정 중 오류 발생");
		}
	}

	@Override
	public Book search(String isbn) {
		try {
			Book book = dao.search(isbn);
			if(book == null) {
				throw new BookException("요청한 책은 등록되지 않은 책 정보입니다.");
			}
			return book;
		} catch (SQLException e) {
			throw new BookException("책 정보 조회 중 오류 발생");
		}
	}
	@Override
	public List<Book> searchAll(PageBean bean) {
		log.debug("searchAll-bean:{}",bean);
		try {
			int total = dao.totalCount(bean);
			log.debug("searchAll-total:{}",total);
			
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), null);
			bean.setPageLink(page.getPageBar());
			return dao.searchAll(bean);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("책 목록 정보 조회 중 오류 발생");
		}
	}
	@Override
	public void remove(String isbn) {
		try {
			dao.remove(isbn);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BookException("책 정보 삭제 중 오류 발생");
		}
	}
}
