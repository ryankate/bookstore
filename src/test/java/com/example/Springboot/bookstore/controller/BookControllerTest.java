package com.example.Springboot.bookstore.controller;

import com.example.Springboot.bookstore.entity.Book;
import com.example.Springboot.bookstore.error.BookNotFoundException;
import com.example.Springboot.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book;
    private List<Book> bookList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .bookId(1L)
                .authorId(Long.valueOf(1))
                .title("Hop on Pop")
                .isbn("0123456789")
                .genre("Children's")
                .pubYear(Long.valueOf(1963))
                .quantity(5)
                .price(4.90)
                .build();
        bookList.add(book);
    }

    @Test
    void saveBook() throws Exception {
        Book inputBook = Book.builder()
                .authorId(Long.valueOf(1))
                .title("Hop on Pop")
                .isbn("0123456789")
                .genre("Children's")
                .pubYear(Long.valueOf(1963))
                .quantity(5)
                .price(4.90)
                .build();
        Mockito.when(bookService.saveBook(inputBook)).thenReturn(book);

        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"authorId\":\"0\",\n" +
                "    \"title\":\"Hop on Pop\",\n" +
                "    \"isbn\":\"0123456789\",\n" +
                "    \"genre\":\"Children's\",\n" +
                "    \"pubYear\":\"1963\",\n" +
                "    \"quantity\":\"5\",\n" +
                "    \"price\":\"4.90\"\n" +
                "}")).andExpect(status().isOk());


    }

    @Test
    void fetchBookById() throws Exception {
        Mockito.when(bookService.fetchBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/books/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    void updateBook() throws Exception {
        Book inputBook = Book.builder()
                .authorId(Long.valueOf(1))
                .title("Hop on Pop")
                .isbn("0123456789")
                .genre("Children's")
                .pubYear(Long.valueOf(1963))
                .quantity(5)
                .price(4.90)
                .build();
        Mockito.when(bookService.updateBook(1L, inputBook)).thenReturn(book);

        mockMvc.perform(put("/books/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"authorId\":\"0\",\n" +
                "    \"title\":\"Hop on Pop\",\n" +
                "    \"isbn\":\"0123456789\",\n" +
                "    \"genre\":\"Children's\",\n" +
                "    \"pubYear\":\"1963\",\n" +
                "    \"quantity\":\"5\",\n" +
                "    \"price\":\"4.90\"\n" +
                "}"))
                .andExpect(status().isOk());

    }

    @Test
    void fetchBookByTitle() throws Exception {
        Mockito.when(bookService.fetchBookByTitle("Hop on Pop")).thenReturn(book);

        mockMvc.perform(get("/books/title/Hop on Pop").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(book.getBookId()));

    }

    //    @Test
//    void fetchBookList() throws Exception {
//        Mockito.when(bookService.fetchBookList()).thenReturn(bookList);
//
//        mockMvc.perform((get("/books").contentType(MediaType.APPLICATION_JSON)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("[\n" +
//                        "    {\n" +
//                        "        \"bookId\": 1,\n" +
//                        "        \"authorId\": 0,\n" +
//                        "        \"title\": \"Hop on Pop\",\n" +
//                        "        \"isbn\": \"0123456789\",\n" +
//                        "        \"genre\": \"Children's\",\n" +
//                        "        \"pubYear\": 1963,\n" +
//                        "        \"quantity\": 5,\n" +
//                        "        \"price\": 4.90\n" +
//                        "    }\n" +
//                        "]", false));
//    }

}