package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books") 
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Create - 책 추가
    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookRequest) {
        return bookService.createBook(bookRequest);
    }

    // Read - 전체 책 조회
    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBook();
    }

    // Read - 특정 책 조회
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    // Update - 책 정보 수정
    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto bookRequest) {
        return bookService.updateBook(id, bookRequest);
    }

    // Delete - 책 삭제
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
