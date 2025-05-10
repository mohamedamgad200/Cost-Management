package com.example.COST.MANAGEMENT.controller;

import com.example.COST.MANAGEMENT.dto.ClientRequest;
import com.example.COST.MANAGEMENT.dto.ClientResponse;
import com.example.COST.MANAGEMENT.dto.CustomResponse;
import com.example.COST.MANAGEMENT.model.Client;
import com.example.COST.MANAGEMENT.repository.ClientRepository;
import com.example.COST.MANAGEMENT.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse>createClient(@Valid @RequestBody ClientRequest request) {
        ClientResponse response = clientService.createClient(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>>getAllClients()
    {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{client-id}")
    public ResponseEntity<ClientResponse>getClientById(@PathVariable("client-id") Long clientId)
    {
        return new ResponseEntity<>(clientService.getClientById(clientId), HttpStatus.OK);
    }

    @PutMapping("/{client-id}")
    public ResponseEntity<ClientResponse>updateClient(@PathVariable("client-id") Long clientId,@Valid @RequestBody ClientRequest request) {
     return new ResponseEntity<>(clientService.updateClient(clientId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{client-id}")
    public ResponseEntity<CustomResponse>DeleteClient(@PathVariable("client-id") Long clientId) {
        return new ResponseEntity<>(clientService.deleteClient(clientId), HttpStatus.OK);
    }
}
