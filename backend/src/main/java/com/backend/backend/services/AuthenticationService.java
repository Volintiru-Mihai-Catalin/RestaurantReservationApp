package com.backend.backend.services;

import com.backend.backend.dto.JwtAuthenticationResponse;
import com.backend.backend.dto.SignInRequest;
import com.backend.backend.dto.SignUpRequest;
import com.backend.backend.models.Client;
import com.backend.backend.models.Role;
import com.backend.backend.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<JwtAuthenticationResponse> signUp(SignUpRequest request) {
        Client client = Client
                .builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(Role.ROLE_USER)
                .build();
        client = clientService.save(client);
        if (client == null)
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        String jwt = jwtService.generateToken(client);

        return new ResponseEntity<>(JwtAuthenticationResponse
                .builder()
                .token(jwt)
                .build(), HttpStatus.CREATED);
    }

    public ResponseEntity<JwtAuthenticationResponse> signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Optional<Client> client = clientRepository.findByEmail(request.getEmail());
        if (client.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        String jwt = jwtService.generateToken(client.get());
        return new ResponseEntity<>(JwtAuthenticationResponse
                .builder()
                .token(jwt)
                .build(), HttpStatus.OK);
    }
}
