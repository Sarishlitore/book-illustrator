package com.example.bookillustrator.dto;

import java.util.List;

public record ChapterResponseDTO(
        Long id,
        String title,
        Long bookId,
        List<ParagraphResponseDTO> paragraphs
) {
}
