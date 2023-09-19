package application.onlinebookstore.mapper;

import application.onlinebookstore.config.MapperConfig;
import application.onlinebookstore.dto.cartitem.CartItemDto;
import application.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import application.onlinebookstore.model.CartItem;
import application.onlinebookstore.model.ShoppingCart;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    @IterableMapping(qualifiedByName = "toDto")
    Set<CartItemDto> mapItems(Set<CartItem> cartItems);

    @AfterMapping
    default void setUserId(@MappingTarget ShoppingCartDto shoppingCartDto,
                           ShoppingCart shoppingCart) {
        shoppingCartDto.setUserId(shoppingCart.getUser().getId());
    }
}
