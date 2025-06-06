package com.shoppingcart.root.service.impl;

import com.shoppingcart.root.entity.CartEntity;
import com.shoppingcart.root.mapper.CartDTOEntityMapper;
import com.shoppingcart.root.modal.Cart;
import com.shoppingcart.root.repository.CartRepository;
import com.shoppingcart.root.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart addCart(Cart cart) {
        if(Objects.isNull(cart)){
            throw new IllegalArgumentException("cart is null");
        }
        final CartEntity cartEntity = CartDTOEntityMapper.map(cart);
        final CartEntity cartEntitySaved = cartRepository.save(cartEntity);
        return CartDTOEntityMapper.map(cartEntitySaved);
    }
}
