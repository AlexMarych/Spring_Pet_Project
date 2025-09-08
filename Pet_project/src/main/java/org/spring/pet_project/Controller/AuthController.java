package org.spring.pet_project.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.LogInRequestDto;
import org.spring.pet_project.Controller.DTO.Request.SignUpRequestDto;
import org.spring.pet_project.Controller.DTO.Response.AuthDataResponse;
import org.spring.pet_project.Security.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/log-in")
    public ResponseEntity<AuthDataResponse> logIn(@Valid @RequestBody LogInRequestDto logInRequestDto) {
        return ResponseEntity.ok(authService.logIn(logInRequestDto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthDataResponse> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(authService.signUp(signUpRequestDto));
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthDataResponse> refresh(@RequestHeader("Authorization") String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken) {
        authService.logout(accessToken);
        return ResponseEntity.noContent().build();
    }

}
