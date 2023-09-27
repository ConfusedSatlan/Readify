package application.onlinebookstore.mapper;

import application.onlinebookstore.config.MapperConfig;
import application.onlinebookstore.dto.orderitem.OrderItemDto;
import application.onlinebookstore.model.OrderItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface OrderItemMapper {
    @Named("toDto")
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "book", source = "bookId", qualifiedByName = "bookFromId")
    OrderItem toModel(OrderItemDto orderItemDto);

    @AfterMapping
    default void setBookTitles(@MappingTarget OrderItemDto orderItemDto, OrderItem orderItem) {
        orderItemDto.setBookTitle(orderItem.getBook().getTitle());
    }

    @AfterMapping
    default void setBookId(@MappingTarget OrderItemDto orderItemDto, OrderItem orderItem) {
        orderItemDto.setBookId(orderItem.getBook().getId());
    }
}
