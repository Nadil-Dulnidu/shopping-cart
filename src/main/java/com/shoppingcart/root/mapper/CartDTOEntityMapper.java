package com.shoppingcart.root.mapper;

import com.shoppingcart.root.entity.CartEntity;
import com.shoppingcart.root.dto.Cart;
import com.shoppingcart.root.dto.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartDTOEntityMapper {
    public static Cart map(CartEntity cartEntity) {
        if (Objects.isNull(cartEntity)) throw new IllegalArgumentException("Cart entity is null");
        Cart cart = new Cart();
        cart.setUserId(cartEntity.getUserId());
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartEntity.getProductId());
        cartItem.setQuantity(cartEntity.getQuantity());
        cart.getCartItems().add(cartItem);
        return cart;
    }

    public static CartEntity map(Cart cart) {
        if (Objects.isNull(cart)) throw new IllegalArgumentException("Cart dto is null");
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(cart.getUserId());
        cartEntity.setProductId(cart.getCartItems().getFirst().getProductId());
        cartEntity.setQuantity(cart.getCartItems().getLast().getQuantity());
        return cartEntity;
    }
}
