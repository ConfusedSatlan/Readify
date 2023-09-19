package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.cartitem.CartItemDto;
import application.onlinebookstore.dto.cartitem.CartItemDtoUpdate;
import application.onlinebookstore.dto.cartitem.CreateCartItemDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.mapper.CartItemMapper;
import application.onlinebookstore.model.CartItem;
import application.onlinebookstore.model.ShoppingCart;
import application.onlinebookstore.repository.book.BookRepository;
import application.onlinebookstore.repository.cartitem.CartItemRepository;
import application.onlinebookstore.repository.shoppingcart.ShoppingCartRepository;
import application.onlinebookstore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemDto save(CreateCartItemDto cartItemDto, Long id) {
        CartItem newCartItem = new CartItem();
        newCartItem.setBook(bookRepository.findById(cartItemDto.bookId()).orElseThrow(
                () -> new EntityNotFoundException("Book with id: " + cartItemDto.bookId()
                        + " not found!")
        ));
        newCartItem.setQuantity(cartItemDto.quantity());
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart with id: " + id
                        + " not found!")
        );
        newCartItem.setShoppingCart(shoppingCart);
        return cartItemMapper.toDto(cartItemRepository.save(newCartItem));
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't delete cart item with id: " + id
                        + ". Cart Item doesn't exist!")
        );
        cartItemRepository.deleteById(id);
    }

    @Override
    public void update(CartItemDtoUpdate updatedCart) {
        Long cartItemId = updatedCart.getId();
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Cart Item with id: " + cartItemId
                        + " not found!")
        );
        cartItem.setQuantity(updatedCart.getQuantity());
        cartItemRepository.save(cartItem);
    }
}
