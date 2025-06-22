package com.example.bookillustrator.mapper;

import com.example.bookillustrator.dto.ParagraphRequestDTO;
import com.example.bookillustrator.dto.ParagraphResponseDTO;
import com.example.bookillustrator.repository.Paragraph;
import com.example.bookillustrator.repository.Sentence;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", uses = SentenceMapper.class)
public interface ParagraphMapper {
    @Mapping(target = "chapterId", source = "chapter.id")
    ParagraphResponseDTO toDTO(Paragraph paragraph);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chapter", ignore = true)
    @Mapping(target = "sentences", ignore = true)
    Paragraph toEntity(ParagraphRequestDTO request, @Context MapperContext context);

    default List<Paragraph> toEntityList(List<ParagraphRequestDTO> dtos, @Context MapperContext context) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(dto -> toEntity(dto, context))
                .toList();
    }

    @AfterMapping
    default void linkSentences(
            ParagraphRequestDTO request,
            @MappingTarget Paragraph paragraph,
            @Context MapperContext context) {
        if (request.sentences() != null) {
            List<Sentence> sentences = context.getSentenceMapper().toEntityList(
                    request.sentences(),
                    context
            );
            sentences.forEach(sentence -> sentence.setParagraph(paragraph));
            paragraph.setSentences(sentences);
        }
    }
}
