package com.estelya.user.api;

import com.estelya.user.application.CreateUserUseCase;
import com.estelya.user.application.GetUserByIdUseCase;
import com.estelya.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenants/{tenantId}/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            GetUserByIdUseCase getUserByIdUseCase) {

        this.createUserUseCase = createUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @PathVariable UUID tenantId,
            @Valid @RequestBody CreateUserRequest request) {

        User user = createUserUseCase.execute(
                tenantId,
                request.name(),
                request.email(),
                request.password(),
                request.role()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.fromDomain(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable UUID tenantId,
            @PathVariable UUID userId) {

        User user =
                getUserByIdUseCase.execute(tenantId, userId);

        return ResponseEntity.ok(
                UserResponse.fromDomain(user));
    }
}