package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.cartitem.CartItemDto;
import application.onlinebookstore.dto.cartitem.CartItemDtoUpdate;
import application.onlinebookstore.dto.cartitem.CreateCartItemDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.mapper.CartItemMapper;
import application.onlinebookstore.model.CartItem;
import application.onlinebookstore.model.ShoppingCart;
import application.onlinebookstore.repository.cartitem.CartItemRepository;
import application.onlinebookstore.service.BookService;
import application.onlinebookstore.service.CartItemService;
import application.onlinebookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private ShoppingCartService shoppingCartService;
    private final BookService bookService;
    private final CartItemMapper cartItemMapper;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                               BookService bookService,
                               CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.bookService = bookService;
        this.cartItemMapper = cartItemMapper;
    }

    @Autowired
    public void setShoppingCartService(@Lazy ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public CartItemDto save(CreateCartItemDto cartItemDto, Long id) {
        CartItem newCartItem = new CartItem();
        newCartItem.setBook(bookService.getEntityBookById(cartItemDto.bookId()));
        newCartItem.setQuantity(cartItemDto.quantity());
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(id);
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
    public CartItemDto update(CartItemDtoUpdate updatedCart, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Cart Item with id: " + cartItemId
                        + " not found!")
        );
        cartItem.setQuantity(updatedCart.quantity());
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }
}
