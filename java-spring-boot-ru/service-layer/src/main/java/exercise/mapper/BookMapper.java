package exercise.mapper;

import exercise.dto.*;
import exercise.model.Author;
import exercise.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class BookMapper {

    // BEGIN
    // Маппинг сущности Book в DTO
    @Mapping(target = "authorId", source = "book.author.id")
    @Mapping(target = "authorFirstName", source = "book.author.firstName")
    @Mapping(target = "authorLastName", source = "book.author.lastName")
    public abstract BookDTO map(Book book);

    // Маппинг DTO в сущность Book
    public abstract Book map(BookCreateDTO bookCreateDTO);

    public abstract Book map(BookDTO bookDTO);
    // END

    @Mapping(target = "author", source = "authorId")
    public abstract void update(BookUpdateDTO dto, @MappingTarget Book model);
}
