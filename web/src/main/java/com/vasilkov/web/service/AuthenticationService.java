package com.vasilkov.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.UserDto;
import com.vasilkov.model.dto.request.SignInRequest;
import com.vasilkov.model.dto.request.SignUpRequest;
import com.vasilkov.model.dto.response.JwtAuthenticationResponse;
import com.vasilkov.model.enums.UserRole;
import com.vasilkov.web.dto.UserSecurityDto;
import com.vasilkov.web.rabbit.producer.UserProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Authentication service.
 *
 * @author Artem Vasilkov
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserProducer producer;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен jwt authentication response
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = UserDto.builder()
                .email(request.getEmail())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.ROLE_USER)
                .build();

        producer.save(user);

        UserSecurityDto userSecurityDto = UserSecurityDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        var jwt = jwtService.generateToken(userSecurityDto);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен jwt authentication response
     * @throws JsonProcessingException the json processing exception
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) throws JsonProcessingException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var user = producer.getByEmail(request.getEmail());
        UserSecurityDto userSecurityDto = UserSecurityDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        var jwt = jwtService.generateToken(userSecurityDto);
        return new JwtAuthenticationResponse(jwt);
    }
}