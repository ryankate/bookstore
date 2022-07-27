package com.example.Springboot.bookstore.service;

import com.example.Springboot.bookstore.entity.Book;
import com.example.Springboot.bookstore.error.BookNotFoundException;

import java.util.List;

public interface BookService {
    public Book saveBook(Book book);

    public List<Book> fetchBookList();

    public Book fetchBookById(Long bookId) throws BookNotFoundException;

    public void deleteBookById(Long bookId);

    public Book updateBook(Long bookId, Book book);

    public Book fetchBookByTitle(String title);
}
