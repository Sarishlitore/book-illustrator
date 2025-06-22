package com.example.bookillustrator.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ChapterRequestDTO(
        @NotBlank String title,
        List<ParagraphRequestDTO> paragraphs
) {
}
