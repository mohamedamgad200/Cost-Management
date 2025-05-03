package com.example.COST.MANAGEMENT.service;

import com.example.COST.MANAGEMENT.dto.ClientRequest;
import com.example.COST.MANAGEMENT.dto.ClientResponse;
import com.example.COST.MANAGEMENT.model.Client;
import com.example.COST.MANAGEMENT.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    public ClientResponse createClient(ClientRequest request)
    {
        Client client= Client.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        Client savedClient = clientRepository.save(client);
        return ClientResponse
                .builder()
                .id(savedClient.getId())
                .name(savedClient.getName())
                .email(savedClient.getEmail())
                .phone(savedClient.getPhone())
                .build();
    }
}
