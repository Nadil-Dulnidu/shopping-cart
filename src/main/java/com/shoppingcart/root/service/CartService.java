package com.shoppingcart.root.service;

import com.shoppingcart.root.dto.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    Cart addCart(Cart cart);
}
