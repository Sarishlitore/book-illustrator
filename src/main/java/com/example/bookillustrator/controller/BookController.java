package com.example.bookillustrator.controller;

import com.example.bookillustrator.repository.Book;
import com.example.bookillustrator.service.BookService;
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
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updateBook(
            @PathVariable long id,
            @RequestParam(required = false) String title) {
        bookService.update(id, title);
    }
}
