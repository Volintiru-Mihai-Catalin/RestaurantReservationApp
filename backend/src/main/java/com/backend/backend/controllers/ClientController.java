package com.backend.backend.controllers;

import com.backend.backend.models.Client;
import com.backend.backend.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Client>> getClients() {
        return clientService.getClients();
    }

    @PostMapping("/client")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/client/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable Integer id) {
        return clientService.updateClient(client, id);
    }

    @DeleteMapping("/client/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id) {
        return clientService.deleteClient(id);
    }
}
