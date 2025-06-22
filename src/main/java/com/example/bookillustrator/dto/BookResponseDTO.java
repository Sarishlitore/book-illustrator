package com.example.bookillustrator.dto;

import java.util.List;

public record BookResponseDTO(
        Long id,
        String author,
        String title,
        List<ChapterResponseDTO> chapters
) {
}
