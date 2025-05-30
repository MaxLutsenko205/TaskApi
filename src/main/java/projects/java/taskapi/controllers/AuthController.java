package projects.java.taskapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.java.taskapi.dtos.AuthRequest;
import projects.java.taskapi.dtos.AuthResponse;
import projects.java.taskapi.dtos.RefreshTokenRequest;
import projects.java.taskapi.services.UserService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @Operation(summary = "авторизация пользователя")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(authRequest));
    }

    @Operation(summary = "регистрация пользователя")
    @PostMapping("/reg")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(authRequest));
    }

    @Operation(summary = "обновление access токена с помощью refresh токена")
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest refreshRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.refreshToken(refreshRequest));
    }
}
