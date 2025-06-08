package com.shoppingcart.root.service;

import com.shoppingcart.root.dto.Cart;
import com.shoppingcart.root.dto.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    Cart addToCart(CartItem  cartItem);

    Cart findByUserId(Integer userId);

    Boolean isExistsProduct(Integer productId);

    Cart updateProductQuantity(Integer productId, CartItem cartItem);
}
