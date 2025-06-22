package com.example.bookillustrator.dto;

public record IllustrationRequestDTO(
        byte[] thumbnail,
        String storagePath,
        String prompt
) {
}
