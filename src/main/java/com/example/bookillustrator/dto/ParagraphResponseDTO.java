package com.example.bookillustrator.dto;

import java.util.List;

public record ParagraphResponseDTO(
        Long id,
        Long chapterId,
        List<SentenceResponseDTO> sentences
) {
}
