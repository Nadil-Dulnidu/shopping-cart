package com.shoppingcart.root.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @JsonProperty("user_id")
    protected Integer id;

    @NotBlank(message = "Username can not be blank")
    @JsonProperty("username")
    protected String username;

    @NotBlank(message = "Password can not be blank")
    @JsonProperty("password")
    protected String password;

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Invalid email format")
    @JsonProperty("email")
    protected String email;

    @NotBlank(message = "Phone can not be blank")
    @JsonProperty("phone")
    protected String phone;

    @JsonProperty("user_role")
    protected Integer role;
}
