package com.shoppingcart.root.service.impl;

import com.shoppingcart.root.dto.CartItem;
import com.shoppingcart.root.entity.CartEntity;
import com.shoppingcart.root.mapper.CartDTOEntityMapper;
import com.shoppingcart.root.dto.Cart;
import com.shoppingcart.root.repository.CartRepository;
import com.shoppingcart.root.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart addToCart(CartItem cartItem) {
        if(Objects.isNull(cartItem)){
            throw new IllegalArgumentException("cart is null");
        }
        final Cart cart = new Cart();
        cart.setUserId(1);
        cart.getCartItems().add(cartItem);
        final CartEntity cartEntity = CartDTOEntityMapper.map(cart);
        final CartEntity cartEntitySaved = cartRepository.save(cartEntity);
        return CartDTOEntityMapper.map(cartEntitySaved);
    }

    @Override
    public Cart findByUserId(Integer userId) {
        if (Objects.isNull(userId)){
            throw new IllegalArgumentException("UserId is null");
        }
        final List<CartEntity> cartEntities = cartRepository.findByUserId(userId);
        if (Objects.isNull(cartEntities) || cartEntities.isEmpty()){
            throw new IllegalArgumentException("Your cart is empty");
        }
        final List<Cart> cartList = cartEntities.stream()
                .map(CartDTOEntityMapper::map)
                .toList();
        final Cart cart = new Cart();
        cart.setUserId(userId);
        for(Cart cart1 : cartList){
            cart.getCartItems().addAll(cart1.getCartItems());
        }
        return cart;
    }

    @Override
    public Boolean isExistsProduct(Integer productId) {
        if (Objects.isNull(productId)){
            throw new IllegalArgumentException("Product Id is null");
        }
        CartEntity cartEntity = cartRepository.findByProductId(productId);
        return Objects.nonNull(cartEntity);
    }

    @Override
    public Cart updateProductQuantity(Integer productId,CartItem cartItem) {
        if (Objects.isNull(cartItem) || Objects.isNull(productId)){
            throw new IllegalArgumentException("Product Id and cartItems are null");
        }
        final CartEntity cartEntity = cartRepository.findByProductId(productId);
        if(Objects.isNull(cartEntity)){
            throw new IllegalArgumentException("cart is null");
        }
        cartEntity.setQuantity(cartEntity.getQuantity() + cartItem.getQuantity());
        cartRepository.save(cartEntity);
        return CartDTOEntityMapper.map(cartEntity);
    }
}
