package com.example.Springboot.bookstore.controller;

import com.example.Springboot.bookstore.entity.Book;
import com.example.Springboot.bookstore.error.BookNotFoundException;
import com.example.Springboot.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/books")
    public Book saveBook(@Valid @RequestBody Book book){
        LOGGER.info("Running saveBook in BookController");
        return bookService.saveBook(book);
    }

    @GetMapping("/books")
    public List<Book> fetchBookList(){
        LOGGER.info("Running fetchBookList in BookController");
        return bookService.fetchBookList();
    }

    @GetMapping("/books/{id}")
    public Book fetchBookById(@PathVariable("id") Long bookId) throws BookNotFoundException {
        LOGGER.info("Running fetchBookById in BookController");
        return bookService.fetchBookById(bookId);
    }

    @DeleteMapping("/books/{id}")
    public String deleteBookById(@PathVariable("id") Long bookId){
        LOGGER.info("Running deleteBookById in BookController");
        bookService.deleteBookById(bookId);
        return "Book deleted successfully!";
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") Long bookId,
                           @RequestBody Book book){
        LOGGER.info("Running updateBook in BookController");
        return bookService.updateBook(bookId, book);
    }

    @GetMapping("/books/title/{title}")
    public Book fetchBookByTitle(@PathVariable("title") String title){
        LOGGER.info("Running fetchBookByTitle in BookController");
        return bookService.fetchBookByTitle(title);
    }
}
