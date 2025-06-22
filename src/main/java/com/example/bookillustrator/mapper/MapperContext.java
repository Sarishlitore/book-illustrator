package com.example.bookillustrator.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@Component
public class MapperContext {
    private final ChapterMapper chapterMapper;
    private final ParagraphMapper paragraphMapper;
    private final SentenceMapper sentenceMapper;
    private final IllustrationMapper illustrationMapper;
}
