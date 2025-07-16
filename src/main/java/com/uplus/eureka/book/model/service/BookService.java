package com.uplus.eureka.book.model.service;

import java.util.List;

import com.uplus.eureka.book.model.dto.Book;
import com.uplus.eureka.book.model.dto.PageBean;

public interface BookService {
	public void insert(Book book);
	public void update(Book book);
	public Book search(String isbn);
	public List<Book> searchAll(PageBean bean);
	public void remove(String isbn);
}
