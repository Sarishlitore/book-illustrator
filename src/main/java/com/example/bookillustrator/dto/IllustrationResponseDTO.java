package com.example.bookillustrator.dto;

public record IllustrationResponseDTO(
        Long id,
        byte[] thumbnail,
        String storagePath,
        String prompt,
        Long sentenceId
) {
}
