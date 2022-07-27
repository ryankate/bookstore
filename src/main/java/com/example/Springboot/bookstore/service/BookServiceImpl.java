package com.example.Springboot.bookstore.service;

import com.example.Springboot.bookstore.entity.Book;
import com.example.Springboot.bookstore.error.BookNotFoundException;
import com.example.Springboot.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> fetchBookList() {
        return bookRepository.findAll();
    }

    @Override
    public Book fetchBookById(Long bookId) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if(!book.isPresent()){
            throw new BookNotFoundException("Book Not Available");
        }
        return book.get();
    }

    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book updateBook(Long bookId, Book book) {
        Book bookDB = bookRepository.findById(bookId).get();

        if(Objects.nonNull(book.getAuthorId())){
            bookDB.setAuthorId(book.getAuthorId());
        }

        if(Objects.nonNull(book.getTitle()) && !"".equalsIgnoreCase(book.getTitle())){
            bookDB.setTitle(book.getTitle());
        }

        if(Objects.nonNull(book.getIsbn()) && !"".equalsIgnoreCase(book.getIsbn())){
            bookDB.setIsbn(book.getIsbn());
        }

        if(Objects.nonNull(book.getGenre()) && !"".equalsIgnoreCase(book.getGenre())){
            bookDB.setGenre(book.getGenre());
        }

        if(Objects.nonNull(book.getPubYear())){
            bookDB.setPubYear(book.getPubYear());
        }

        if(Objects.nonNull(book.getQuantity())){
            bookDB.setQuantity(book.getQuantity());
        }

        if(Objects.nonNull(book.getPrice())){
            bookDB.setPrice(book.getPrice());
        }

        return bookRepository.save(bookDB);
    }

    @Override
    public Book fetchBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }
}
