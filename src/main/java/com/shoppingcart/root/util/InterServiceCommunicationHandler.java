package com.shoppingcart.root.util;

import com.shoppingcart.root.dto.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class InterServiceCommunicationHandler {

    @Value("${user.service.url}")
    private String userServiceUrl;

    private final WebClient webClient;

    @Autowired
    public InterServiceCommunicationHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    private String getJwt(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public User interServiceCallByWebClient(String username,HttpServletRequest request) {
        return webClient.get()
                .uri(userServiceUrl+username)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwt(request))
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }
}
