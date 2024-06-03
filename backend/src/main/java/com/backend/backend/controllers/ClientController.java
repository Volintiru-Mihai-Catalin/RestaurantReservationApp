package com.backend.backend.controllers;

import com.backend.backend.models.Client;
import com.backend.backend.services.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getClients() {
        return clientService.getClients();
    }

    @PostMapping("/client")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/client/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateClient(@RequestBody Client client, @PathVariable Integer id) {
        return clientService.updateClient(client, id);
    }

    @DeleteMapping("/client/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteClient(@PathVariable Integer id) {
        return clientService.deleteClient(id);
    }
}
