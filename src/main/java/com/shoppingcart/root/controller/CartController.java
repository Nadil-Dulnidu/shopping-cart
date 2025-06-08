package com.shoppingcart.root.controller;

import com.shoppingcart.root.security.AuthContext;
import com.shoppingcart.root.dto.Cart;
import com.shoppingcart.root.dto.CartItem;
import com.shoppingcart.root.service.CartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItem item, HttpSession session){
        String username = AuthContext.getAuthUsername();
        if(username != null){
            final Boolean isExistsProduct = cartService.isExistsProduct(item.getProductId());
            if(isExistsProduct){
                Cart UpdatedCart = cartService.updateProductQuantity(item.getProductId(),item);
                return ResponseEntity.ok().body(UpdatedCart);
            }
            final Cart savedCart = cartService.addToCart(item);
            return ResponseEntity.ok().body(savedCart);
        }else{
            Cart cart = (Cart) session.getAttribute("guestCart");
            if (cart == null) cart = new Cart();
            boolean itemExists = false;
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getProductId().equals(item.getProductId())) {
                    cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                    itemExists = true;
                    break;
                }
            }
            if (!itemExists) cart.getCartItems().add(item);
            session.setAttribute("guestCart", cart);
            return ResponseEntity.ok("Item added successfully");
        }
    }

    @GetMapping()
    public ResponseEntity<?> viewCart(HttpSession session) {
        String username = AuthContext.getAuthUsername();
        if(username != null){
            final Cart cartList = cartService.findByUserId(1);
            return ResponseEntity.ok(cartList);
        }
        Cart cart = (Cart) session.getAttribute("guestCart");
        return ResponseEntity.ok(cart);
    }
}
