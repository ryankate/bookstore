package com.example.Springboot.bookstore.repository;

import com.example.Springboot.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Book findByTitle(String title);

    public Book findByTitleIgnoreCase(String title);
}
