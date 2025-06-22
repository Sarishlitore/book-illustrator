package com.example.bookillustrator.dto;

import java.util.List;

public record SentenceRequestDTO(
        String content,
        List<IllustrationRequestDTO> illustrations
) {
}
