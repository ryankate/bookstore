package com.example.Springboot.bookstore.service;

import com.example.Springboot.bookstore.entity.Book;
import com.example.Springboot.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book book = Book.builder()
                .bookId(Long.valueOf(1))
                .authorId(Long.valueOf(2))
                .title("Hop on Pop")
                .isbn("123456789")
                .genre("Children's")
                .pubYear(Long.valueOf(1963))
                .quantity(2)
                .price(4.19)
                .build();

        Mockito.when(bookRepository.findByTitleIgnoreCase("Hop on Pop")).thenReturn(book);
    }
    @Test
    @DisplayName("Get Data Based on Valid Book Title")
    public void whenValidBookTitle_thenBookShouldBeFound(){
        String bookTitle = "Hop on Pop";
        Book found = bookService.fetchBookByTitle(bookTitle);

        assertEquals(bookTitle, found.getTitle());
    }
}