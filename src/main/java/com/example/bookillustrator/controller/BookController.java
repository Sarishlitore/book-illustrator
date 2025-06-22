package com.example.bookillustrator.controller;

import com.example.bookillustrator.dto.BookRequestDTO;
import com.example.bookillustrator.dto.BookResponseDTO;
import com.example.bookillustrator.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDTO> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(
            @Valid @RequestBody BookRequestDTO bookRequest
    ) {
        BookResponseDTO response = bookService.create(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updateBook(
            @PathVariable Long id,
            @RequestParam(required = false) String title) {
        bookService.update(id, title);
    }
}
