package com.backend.backend.services;

import com.backend.backend.models.Client;
import com.backend.backend.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Client> addClient(Client client) {
        Optional<Client> clientOptional = clientRepository.findByEmail(client.getEmail());

        if (clientOptional.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
    }

    public ResponseEntity<Client> updateClient(Client client, Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Client clientToUpdate = clientOptional.get();

        Optional<Client> optionalClient = clientRepository.findByEmail(client.getEmail());
        if (optionalClient.isPresent()) {
            if (!clientToUpdate.getId().equals(optionalClient.get().getId())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }

        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setFirstName(client.getFirstName());
        clientToUpdate.setLastName(client.getLastName());

        return new ResponseEntity<>(clientRepository.save(clientToUpdate), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteClient(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        clientRepository.delete(optionalClient.get());
        return new ResponseEntity<>("Client deleted", HttpStatus.NOT_FOUND);
    }
}