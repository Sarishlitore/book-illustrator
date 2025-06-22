package com.example.bookillustrator.service;

import com.example.bookillustrator.dto.BookRequestDTO;
import com.example.bookillustrator.dto.BookResponseDTO;
import com.example.bookillustrator.mapper.BookMapper;
import com.example.bookillustrator.mapper.MapperContext;
import com.example.bookillustrator.repository.Book;
import com.example.bookillustrator.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final MapperContext mapperContext;


    public List<BookResponseDTO> getBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO create(BookRequestDTO request) {
        Book book = bookMapper.toEntity(request, mapperContext);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    public void delete(Long id) {
    }

    @Transactional
    public void update(Long id, String title) {
    }
}
