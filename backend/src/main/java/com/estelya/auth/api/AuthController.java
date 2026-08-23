package com.estelya.auth.api;

import com.estelya.auth.application.LoginUseCase;
import com.estelya.user.api.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginUseCase.LoginResult result =
                loginUseCase.execute(
                        request.tenantId(),
                        request.email(),
                        request.password()
                );

        return ResponseEntity.ok(
                new LoginResponse(
                        result.accessToken(),
                        "Bearer",
                        result.expiresIn(),
                        UserResponse.fromDomain(result.user())
                )
        );
    }
}