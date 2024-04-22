package com.backend.backend.config;

import com.backend.backend.models.Client;
import com.backend.backend.models.Role;
import com.backend.backend.repositories.ClientRepository;
import com.backend.backend.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeedDataConfig implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientService clientService;


    @Override
    public void run(String... args) throws Exception {
        if (clientRepository.count() == 0) {
            Client admin = Client
                    .builder()
                    .email("admin@admin.com")
                    .firstName("admin")
                    .lastName("admin")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ROLE_ADMIN)
                    .phoneNumber("0000000000")
                    .build();

            clientService.save(admin);
        }
    }
}
