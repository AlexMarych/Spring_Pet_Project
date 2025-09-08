package org.spring.pet_project.Security.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.LogInRequestDto;
import org.spring.pet_project.Controller.DTO.Request.SignUpRequestDto;
import org.spring.pet_project.Controller.DTO.Response.AuthDataResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Transactional
    public AuthDataResponse logIn(LogInRequestDto logInRequestDto) {
        return null;
    }

    @Transactional
    public AuthDataResponse signUp(SignUpRequestDto signUpRequestDto) {
        return null;
    }

    @Transactional
    public AuthDataResponse refresh(String refreshToken) {
        return null;
    }

    @Transactional
    public void logout(String accessToken) {
    }
}
