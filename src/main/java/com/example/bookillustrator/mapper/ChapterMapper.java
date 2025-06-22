package com.example.bookillustrator.mapper;

import com.example.bookillustrator.dto.ChapterRequestDTO;
import com.example.bookillustrator.dto.ChapterResponseDTO;
import com.example.bookillustrator.repository.Chapter;
import com.example.bookillustrator.repository.Paragraph;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", uses = ParagraphMapper.class)
public interface ChapterMapper {
    @Mapping(target = "bookId", source = "book.id")
    ChapterResponseDTO toDto(Chapter chapter);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "paragraphs", ignore = true)
    Chapter toEntity(ChapterRequestDTO request, @Context MapperContext context);

    default List<Chapter> toEntityList(List<ChapterRequestDTO> dtos, @Context MapperContext context) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(dto -> toEntity(dto, context))
                .toList();
    }

    @AfterMapping
    default void linkParagraphs(
            ChapterRequestDTO request,
            @MappingTarget Chapter chapter,
            @Context MapperContext context) {
        if (request.paragraphs() != null) {
            List<Paragraph> paragraphs = context.getParagraphMapper().toEntityList(
                    request.paragraphs(),
                    context
            );
            paragraphs.forEach(paragraph -> paragraph.setChapter(chapter));
            chapter.setParagraphs(paragraphs);
        }
    }
}
