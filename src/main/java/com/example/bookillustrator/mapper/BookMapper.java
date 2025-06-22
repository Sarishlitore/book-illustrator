package com.example.bookillustrator.mapper;

import com.example.bookillustrator.dto.BookRequestDTO;
import com.example.bookillustrator.dto.BookResponseDTO;
import com.example.bookillustrator.repository.Book;
import com.example.bookillustrator.repository.Chapter;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChapterMapper.class)
public interface BookMapper {
    BookResponseDTO toDTO(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chapters", ignore = true)
    Book toEntity(BookRequestDTO request, @Context MapperContext context);

    @AfterMapping
    default void linkChapters(
            BookRequestDTO request,
            @MappingTarget Book book,
            @Context MapperContext context) {
        if (request.chapters() != null) {
            List<Chapter> chapters = context.getChapterMapper().toEntityList(
                    request.chapters(),
                    context
            );
            chapters.forEach(chapter -> chapter.setBook(book));
            book.setChapters(chapters);
        }
    }
}
