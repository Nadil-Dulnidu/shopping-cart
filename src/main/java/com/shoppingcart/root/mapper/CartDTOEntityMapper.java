package com.shoppingcart.root.mapper;

import com.shoppingcart.root.entity.CartEntity;
import com.shoppingcart.root.dto.Cart;
import com.shoppingcart.root.dto.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartDTOEntityMapper {
    public static Cart map(CartEntity cartEntity) {
        Cart cart = new Cart();
        cart.setUserId(cartEntity.getUserId());
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartEntity.getProductId());
        cartItem.setQuantity(cartEntity.getQuantity());
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cart.setCartItems(cartItems);
        return cart;
    }

    public static CartEntity map(Cart cart) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(cart.getUserId());
        cartEntity.setProductId(cart.getCartItems().getFirst().getProductId());
        cartEntity.setQuantity(cart.getCartItems().getLast().getQuantity());
        return cartEntity;
    }
}
