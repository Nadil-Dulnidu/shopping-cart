package com.shoppingcart.root.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItem {
    @JsonProperty("product_id")
    @NotBlank(message = "product id can not be null")
    private Integer productId;

    @JsonProperty("quantity")
    @NotNull(message = "quantity can not be null")
    @Min(value = 1, message = "cart quantity must be a positive number")
    private Integer quantity;
}
