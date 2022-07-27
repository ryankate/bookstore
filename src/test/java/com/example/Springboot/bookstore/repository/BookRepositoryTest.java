package com.example.Springboot.bookstore.repository;

import com.example.Springboot.bookstore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Book book = Book.builder()
                .authorId(Long.valueOf(1))
                .title("Hop on Pop")
                .isbn("0123456789")
                .genre("Children's")
                .pubYear(Long.valueOf(1963))
                .quantity(5)
                .price(4.90)
                .build();

        entityManager.persist(book);
    }
    @Test
    public void whenFindByIdThenReturnBook(){
        Book book = bookRepository.findById(1L).get();
        assertEquals(book.getTitle(), "Hop on Pop");
    }
}