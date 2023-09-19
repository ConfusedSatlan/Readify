package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.cartitem.CartItemDto;
import application.onlinebookstore.dto.cartitem.CartItemDtoUpdate;
import application.onlinebookstore.dto.cartitem.CreateCartItemDto;
import application.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.exception.ShoppingCartException;
import application.onlinebookstore.mapper.CartItemMapper;
import application.onlinebookstore.mapper.ShoppingCartMapper;
import application.onlinebookstore.model.CartItem;
import application.onlinebookstore.model.ShoppingCart;
import application.onlinebookstore.model.User;
import application.onlinebookstore.repository.shoppingcart.ShoppingCartRepository;
import application.onlinebookstore.repository.user.UserRepository;
import application.onlinebookstore.service.CartItemService;
import application.onlinebookstore.service.ShoppingCartService;
import java.beans.Transient;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final CartItemService cartItemService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transient
    public ShoppingCartDto addCartItem(CreateCartItemDto cartItemDtoRequest, Long userId) {
        Optional<ShoppingCart> scByUserId = shoppingCartRepository.findByUserId(userId);
        if (scByUserId.isEmpty()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(getUserById(userId));
            shoppingCart.setCartItems(new HashSet<>());
            ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
            Long id = savedShoppingCart.getId();
            CartItemDto savedCartItem = cartItemService.save(cartItemDtoRequest, id);
            savedShoppingCart.getCartItems().add(cartItemMapper.toModel(savedCartItem));
            return shoppingCartMapper.toDto(shoppingCartRepository.save(savedShoppingCart));
        }
        ShoppingCart shoppingCart = scByUserId.get();
        CartItemDto savedCartItem = cartItemService.save(cartItemDtoRequest, shoppingCart.getId());
        shoppingCart.getCartItems().add(cartItemMapper.toModel(savedCartItem));
        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public ShoppingCartDto getShoppingCart(Long id) {
        ShoppingCart shoppingCart = getShoppingCartFromUserId(id);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteByCartItemId(Long id, Long userId) {
        ShoppingCart scByUserId = getShoppingCartFromUserId(userId);
        Set<CartItem> cartItems = scByUserId.getCartItems();
        Optional<CartItem> first = cartItems.stream()
                .filter(cartItem -> Objects.equals(cartItem.getId(), id))
                .findFirst();
        first.ifPresent(cartItem -> cartItemService.deleteById(cartItem.getId()));
        first.ifPresent(cartItems::remove);
        shoppingCartRepository.save(scByUserId);
    }

    @Override
    public void deleteByBookId(Long id, Long userId) {
        ShoppingCart shoppingCartFromUserId = getShoppingCartFromUserId(userId);
        Set<CartItem> cartItems = shoppingCartFromUserId.getCartItems();
        Optional<CartItem> first = cartItems.stream()
                .filter(cartItem -> Objects.equals(cartItem.getBook().getId(), id))
                .findFirst();
        first.ifPresent(cartItem -> cartItemService.deleteById(cartItem.getId()));
    }

    @Override
    public void delete(Long id) {
        ShoppingCart shoppingCartFromUserId = getShoppingCartFromUserId(id);
        Set<CartItem> cartItems = shoppingCartFromUserId.getCartItems();
        for (CartItem cartItem : cartItems) {
            cartItemService.deleteById(cartItem.getId());
        }
        shoppingCartRepository.delete(shoppingCartFromUserId);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id: " + id
                        + " not found in db")
        );
    }

    @Override
    public ShoppingCartDto updateCartItem(Long id, CartItemDtoUpdate cartItemDto) {
        cartItemService.update(cartItemDto);
        return shoppingCartMapper.toDto(getShoppingCartFromUserId(id));
    }

    private ShoppingCart getShoppingCartFromUserId(Long id) {
        return shoppingCartRepository.findByUserId(id).orElseThrow(
                () -> new ShoppingCartException("Shopping cart is empty! In user id: " + id
                        + " Add some items."));
    }
}
