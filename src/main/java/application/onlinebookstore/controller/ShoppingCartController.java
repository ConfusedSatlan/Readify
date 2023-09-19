package application.onlinebookstore.controller;

import application.onlinebookstore.dto.cartitem.CartItemDtoUpdate;
import application.onlinebookstore.dto.cartitem.CreateCartItemDto;
import application.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import application.onlinebookstore.model.User;
import application.onlinebookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing user's cart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get a shopping cart")
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getShoppingCart(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Add an item to a cart")
    public ShoppingCartDto addToCart(
            @RequestBody CreateCartItemDto request,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addCartItem(request, user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cart-item/{id}")
    @Operation(summary = "Update quantity of cart item by cart item id, request \"quantity\" ")
    public ShoppingCartDto updateCartItem(@PathVariable Long id,
                                          @RequestBody CartItemDtoUpdate cartItemDto,
                                          Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        cartItemDto.setId(id);
        return shoppingCartService.updateCartItem(user.getId(), cartItemDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/cart-item/{id}")
    @Operation(summary = "Delete cart item by cart item id")
    public void deleteCartItem(@PathVariable Long id,
                               Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.deleteByCartItemId(id, user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/book/{id}")
    @Operation(summary = "Delete cart item by book id")
    public void deleteCartItemByBook(@PathVariable Long id,
                                     Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.deleteByBookId(id, user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping
    @Operation(summary = "Delete shopping cart")
    public void deleteShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.delete(user.getId());
    }
}
