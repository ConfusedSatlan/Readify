package application.onlinebookstore.mapper;

import application.onlinebookstore.config.MapperConfig;
import application.onlinebookstore.dto.cartitem.CartItemDto;
import application.onlinebookstore.model.CartItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface CartItemMapper {
    @Named("toDto")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(target = "book", source = "bookId", qualifiedByName = "bookFromId")
    CartItem toModel(CartItemDto cartItemDto);

    @AfterMapping
    default void setBookTitles(@MappingTarget CartItemDto cartItemDto, CartItem cartItem) {
        cartItemDto.setBookTitle(cartItem.getBook().getTitle());
    }

    @AfterMapping
    default void setBookId(@MappingTarget CartItemDto cartItemDto, CartItem cartItem) {
        cartItemDto.setBookId(cartItem.getBook().getId());
    }
}
