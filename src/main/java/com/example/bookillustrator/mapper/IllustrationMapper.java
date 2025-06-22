package com.example.bookillustrator.mapper;

import com.example.bookillustrator.dto.IllustrationRequestDTO;
import com.example.bookillustrator.dto.IllustrationResponseDTO;

import com.example.bookillustrator.repository.Illustration;
import com.example.bookillustrator.repository.Sentence;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IllustrationMapper {
    @Mapping(target = "sentenceId", source = "sentence.id")
    IllustrationResponseDTO toDTO(Sentence sentence);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sentence", ignore = true)
    Illustration toEntity(IllustrationRequestDTO sentence, @Context MapperContext context);

    default List<Illustration> toEntityList(List<IllustrationRequestDTO> dtos, @Context MapperContext context) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(dto -> toEntity(dto, context))
                .toList();
    }
}
