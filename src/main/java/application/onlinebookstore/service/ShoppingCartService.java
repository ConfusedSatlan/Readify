package application.onlinebookstore.service;

import application.onlinebookstore.dto.cartitem.CreateCartItemDto;
import application.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import application.onlinebookstore.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartDto addCartItem(CreateCartItemDto cartItemDto, Long userId);

    ShoppingCartDto getShoppingCart(Long userId);

    void deleteByBookId(Long id, Long userId);

    void delete(Long id);

    ShoppingCart getShoppingCartById(Long id);
}
