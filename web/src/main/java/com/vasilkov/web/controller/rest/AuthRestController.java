package com.vasilkov.web.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vasilkov.model.dto.request.SignInRequest;
import com.vasilkov.model.dto.request.SignUpRequest;
import com.vasilkov.model.dto.response.JwtAuthenticationResponse;
import com.vasilkov.web.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth rest controller.
 *
 * @author Artem Vasilkov
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthRestController {
    private final AuthenticationService authenticationService;

    /**
     * Sign up jwt authentication response.
     *
     * @param request the request
     * @return the jwt authentication response
     */
    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    /**
     * Sign in jwt authentication response.
     *
     * @param request the request
     * @return the jwt authentication response
     * @throws JsonProcessingException the json processing exception
     */
    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) throws JsonProcessingException {
        return authenticationService.signIn(request);
    }
}