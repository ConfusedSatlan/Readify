package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.cartitem.CartItemDto;
import application.onlinebookstore.dto.cartitem.CreateCartItemDto;
import application.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.exception.ShoppingCartException;
import application.onlinebookstore.mapper.CartItemMapper;
import application.onlinebookstore.mapper.ShoppingCartMapper;
import application.onlinebookstore.model.CartItem;
import application.onlinebookstore.model.ShoppingCart;
import application.onlinebookstore.repository.shoppingcart.ShoppingCartRepository;
import application.onlinebookstore.service.CartItemService;
import application.onlinebookstore.service.ShoppingCartService;
import application.onlinebookstore.service.UserService;
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
    private final UserService userService;
    private final CartItemService cartItemService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartDto addCartItem(CreateCartItemDto cartItemDtoRequest, Long userId) {
        Optional<ShoppingCart> shoppingCartByUserId =
                shoppingCartRepository.findByUserId(userId);
        if (shoppingCartByUserId.isEmpty()) {
            return createCartItem(userId, cartItemDtoRequest);
        }
        ShoppingCart shoppingCart = shoppingCartByUserId.get();
        CartItemDto savedCartItem = cartItemService.save(cartItemDtoRequest, shoppingCart.getId());
        shoppingCart.getCartItems().add(cartItemMapper.toModel(savedCartItem));
        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public ShoppingCartDto getShoppingCart(Long id) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(id);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteByCartItemId(Long id, Long userId) {
        ShoppingCart shoppingCartForDelete = getShoppingCartByUserId(userId);
        Set<CartItem> cartItems = shoppingCartForDelete.getCartItems();
        Optional<CartItem> first = cartItems.stream()
                .filter(cartItem -> Objects.equals(cartItem.getId(), id))
                .findFirst();
        first.ifPresent(cartItem -> cartItemService.deleteById(cartItem.getId()));
        first.ifPresent(cartItems::remove);
        shoppingCartRepository.save(shoppingCartForDelete);
    }

    @Override
    public void deleteByBookId(Long id, Long userId) {
        ShoppingCart shoppingCartFromUserId = getShoppingCartByUserId(userId);
        Set<CartItem> cartItems = shoppingCartFromUserId.getCartItems();
        Optional<CartItem> first = cartItems.stream()
                .filter(cartItem -> Objects.equals(cartItem.getBook().getId(), id))
                .findFirst();
        first.ifPresent(cartItem -> cartItemService.deleteById(cartItem.getId()));
    }

    @Override
    public void delete(Long id) {
        ShoppingCart shoppingCartFromUserId = getShoppingCartByUserId(id);
        shoppingCartRepository.delete(shoppingCartFromUserId);
    }

    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart with id: " + id
                        + " not found!")
        );
    }

    private ShoppingCartDto createCartItem(Long userId, CreateCartItemDto cartItemDtoRequest) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userService.getUserById(userId));
        shoppingCart.setCartItems(new HashSet<>());
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
        Long id = savedShoppingCart.getId();
        CartItemDto savedCartItem = cartItemService.save(cartItemDtoRequest, id);
        savedShoppingCart.getCartItems().add(cartItemMapper.toModel(savedCartItem));
        return shoppingCartMapper.toDto(shoppingCartRepository.save(savedShoppingCart));
    }

    private ShoppingCart getShoppingCartByUserId(Long id) {
        return shoppingCartRepository.findByUserId(id).orElseThrow(
                () -> new ShoppingCartException("Shopping cart is empty! In user id: " + id
                        + " Add some items."));
    }
}
