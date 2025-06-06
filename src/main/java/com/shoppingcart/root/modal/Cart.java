package com.shoppingcart.root.modal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @NotNull(message = "user id can not be null")
    private Integer userId;

    @NotNull(message = "cart items can not be null")
    private List<CartItem>  cartItems = new ArrayList<>();
}
