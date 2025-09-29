package com.alkateca.mailing.client; // Crie o pacote se não existir

import com.alkateca.mailing.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserApiClient {

    private final RestTemplate restTemplate;

    @Value("${api.login.base-url}")
    private String loginApiBaseUrl;

    public UserApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserResponseDTO findUserById(Long userId) {
        String url = loginApiBaseUrl + "/users/" + userId;

        return restTemplate.getForObject(url, UserResponseDTO.class);
    }
}