package com.shoppingcart.root.controller;

import com.shoppingcart.root.security.AuthContext;
import com.shoppingcart.root.dto.Cart;
import com.shoppingcart.root.dto.CartItem;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@Valid @RequestBody CartItem item, HttpSession session){
        String username = AuthContext.getAuthUsername();
        if(username != null){
            return ResponseEntity.ok(username);
            //
             //
             //
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
        Cart cart = (Cart) session.getAttribute("guestCart");
        return ResponseEntity.ok(cart);
    }
}
