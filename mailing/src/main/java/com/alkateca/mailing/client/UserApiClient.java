package com.alkateca.mailing.client; // Crie o pacote se não existir

import com.alkateca.mailing.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserApiClient {

    private final RestTemplate restTemplate;

    // Injetando a URL base da API de usuários a partir do application.properties
    @Value("${api.login.base-url}")
    private String loginApiBaseUrl;

    public UserApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserResponseDTO findUserById(Long userId) {
        // Constrói a URL completa: http://localhost:8080/users/1
        String url = loginApiBaseUrl + "/users/" + userId;

        // Faz a chamada GET, espera uma resposta JSON e a converte para nosso DTO
        return restTemplate.getForObject(url, UserResponseDTO.class);
    }
}