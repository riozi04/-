package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.dto.BookDto;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Create
    public BookDto createBook(BookDto dto) {
        Book book = dto.toEntity();
        Book saved = bookRepository.save(book);
        return BookDto.fromEntity(saved);
    }

    // Read - 전체 조회
    public List<BookDto> getAllBook() {
        return bookRepository.findAll()
                .stream()
                .map(BookDto::fromEntity)
                .collect(Collectors.toList());
    }

    // Read - 단건 조회
    public BookDto getBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(BookDto::fromEntity).orElse(null);
    }

    // Update
    public BookDto updateBook(Long id, BookDto dto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setPrice(dto.getPrice());
            Book updated = bookRepository.save(book);
            return BookDto.fromEntity(updated);
        }
        return null;
    }

    // Delete
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
