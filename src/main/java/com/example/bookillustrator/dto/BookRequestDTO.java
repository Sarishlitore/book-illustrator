package com.example.bookillustrator.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record BookRequestDTO(
        @NotBlank String title,
        @NotBlank String author,
        List<ChapterRequestDTO> chapters
) {
}
