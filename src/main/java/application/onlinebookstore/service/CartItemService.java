package application.onlinebookstore.service;

import application.onlinebookstore.dto.cartitem.CartItemDto;
import application.onlinebookstore.dto.cartitem.CartItemDtoUpdate;
import application.onlinebookstore.dto.cartitem.CreateCartItemDto;

public interface CartItemService {
    CartItemDto save(CreateCartItemDto cartItemDto, Long id);

    void deleteById(Long id);

    CartItemDto update(CartItemDtoUpdate updatedCart, Long cartItemId);
}
