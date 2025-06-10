package com.shoppingcart.root.util;

import com.shoppingcart.root.dto.User;
import com.shoppingcart.root.security.AuthContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class InterServiceCommunicationHandler {

    @Value("${user.service.url}")
    private String userServiceUrl;

    private final WebClient webClient;

    @Autowired
    public InterServiceCommunicationHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    public User interServiceCallByWebClient(String username,HttpServletRequest request) {
        return webClient.get()
                .uri(userServiceUrl+username)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + AuthContext.getJwt(request))
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }
}
