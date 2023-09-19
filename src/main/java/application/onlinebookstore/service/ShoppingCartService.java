package application.onlinebookstore.service;

import application.onlinebookstore.dto.cartitem.CartItemDtoUpdate;
import application.onlinebookstore.dto.cartitem.CreateCartItemDto;
import application.onlinebookstore.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {

    ShoppingCartDto updateCartItem(Long id, CartItemDtoUpdate cartItemDto);

    ShoppingCartDto addCartItem(CreateCartItemDto cartItemDto, Long userId);

    ShoppingCartDto getShoppingCart(Long userId);

    void deleteByCartItemId(Long id, Long userId);

    void deleteByBookId(Long id, Long userId);

    void delete(Long id);
}
