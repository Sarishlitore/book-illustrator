package com.example.bookillustrator.dto;

import java.util.List;

public record ParagraphRequestDTO(
        List<SentenceRequestDTO> sentences
) {
}
