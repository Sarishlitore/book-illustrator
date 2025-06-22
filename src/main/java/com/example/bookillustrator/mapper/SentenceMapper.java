package com.example.bookillustrator.mapper;

import com.example.bookillustrator.dto.SentenceRequestDTO;
import com.example.bookillustrator.dto.SentenceResponseDTO;
import com.example.bookillustrator.repository.Illustration;
import com.example.bookillustrator.repository.Sentence;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", uses = IllustrationMapper.class)
public interface SentenceMapper {
    @Mapping(target = "paragraphId", source = "paragraph.id")
    SentenceResponseDTO toDTO(Sentence sentence);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paragraph", ignore = true)
    @Mapping(target = "illustrations", ignore = true)
    Sentence toEntity(SentenceRequestDTO sentence, @Context MapperContext context);

    default List<Sentence> toEntityList(List<SentenceRequestDTO> dtos, @Context MapperContext context) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(dto -> toEntity(dto, context))
                .toList();
    }

    @AfterMapping
    default void linkIllustrations(
            SentenceRequestDTO request,
            @MappingTarget Sentence sentence,
            @Context MapperContext context) {
        if (request.illustrations() != null) {
            List<Illustration> illustrations = context.getIllustrationMapper().toEntityList(
                    request.illustrations(),
                    context
            );
            illustrations.forEach(illustration -> illustration.setSentence(sentence));
            sentence.setIllustrations(illustrations);
        }
    }
}
