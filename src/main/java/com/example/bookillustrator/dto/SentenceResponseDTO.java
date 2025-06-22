package com.example.bookillustrator.dto;

import java.util.List;

public record SentenceResponseDTO(
        Long id,
        Long paragraphId,
        String content,
        List<IllustrationResponseDTO> illustrations
) {
}
