package com.backend.backend.services;

import com.backend.backend.dto.JwtAuthenticationResponse;
import com.backend.backend.dto.SignInRequest;
import com.backend.backend.dto.SignUpRequest;
import com.backend.backend.models.Client;
import com.backend.backend.models.Role;
import com.backend.backend.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
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
        String jwt = jwtService.generateToken(client);

        return JwtAuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Client client = clientRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwt = jwtService.generateToken(client);
        return JwtAuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }
}
