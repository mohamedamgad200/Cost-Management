package com.example.COST.MANAGEMENT.service;

import com.example.COST.MANAGEMENT.dto.ClientRequest;
import com.example.COST.MANAGEMENT.dto.ClientResponse;
import com.example.COST.MANAGEMENT.dto.CustomResponse;
import com.example.COST.MANAGEMENT.exception.NotFoundException;
import com.example.COST.MANAGEMENT.mapper.ClientMapper;
import com.example.COST.MANAGEMENT.model.Client;
import com.example.COST.MANAGEMENT.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientResponse createClient(ClientRequest request)
    {
        Client client= clientMapper.toClient(request);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toClientResponse(savedClient);
    }
    public List<ClientResponse> getAllClients()
    {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(clientMapper::toClientResponse).toList();
    }
    public ClientResponse getClientById(Long clientId)
    {
        Client client=clientRepository.findById(clientId).orElseThrow(()->
                        new NotFoundException("Client with id " + clientId + " not found")
                );
        return clientMapper.toClientResponse(client);
    }
    public ClientResponse updateClient(Long id, ClientRequest request)
    {
        Client client=clientRepository.findById(id).orElseThrow(()->new NotFoundException("Client with id " + id + " not found"));
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        Client saveClient=clientRepository.save(client);
        return clientMapper.toClientResponse(saveClient);
    }
    public CustomResponse deleteClient(Long id)
    {
        clientRepository.findById(id).orElseThrow(()->new NotFoundException("Client with id " + id + " not found"));
        clientRepository.deleteById(id);
        return new CustomResponse("Client Deleted Successfully", LocalDateTime.now());
    }
}
