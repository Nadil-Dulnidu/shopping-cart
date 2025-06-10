package com.shoppingcart.root.service;

import com.shoppingcart.root.dto.Cart;
import com.shoppingcart.root.dto.CartItem;
import com.shoppingcart.root.dto.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    Cart addToCart(CartItem cartItem,String username, HttpServletRequest request);

    Cart findByUserId(Integer userId);

    Boolean isExistsProduct(Integer productId);

    Cart updateProductQuantity(Integer productId, CartItem cartItem);

    User getFinalizeUser(String username, HttpServletRequest request);
}
