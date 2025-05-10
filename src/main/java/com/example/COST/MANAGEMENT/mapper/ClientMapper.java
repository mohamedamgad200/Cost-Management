package com.example.COST.MANAGEMENT.mapper;

import com.example.COST.MANAGEMENT.dto.ClientRequest;
import com.example.COST.MANAGEMENT.dto.ClientResponse;
import com.example.COST.MANAGEMENT.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    public Client toClient(ClientRequest clientRequest){
        return Client.builder()
                .name(clientRequest.getName())
                .email(clientRequest.getEmail())
                .phone(clientRequest.getPhone())
                .build();
    }

    public ClientResponse toClientResponse(Client client){
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .build();
    }
}
