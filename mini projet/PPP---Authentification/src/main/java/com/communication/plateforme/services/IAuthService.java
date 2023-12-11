package com.communication.plateforme.services;

import com.communication.plateforme.model.User;
import com.communication.plateforme.model.VerificationToken;

import com.communication.plateforme.utils.payload.request.LoginRequest;
import com.communication.plateforme.utils.payload.request.SignUpRequest;
import com.communication.plateforme.utils.payload.response.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public interface IAuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    void registerUser(SignUpRequest signUpRequest);
    User getCurrentUser();
    void fetchUserAndEnable(VerificationToken verificationToken);
    boolean isLoggedIn();
    void verifyAccount(String token);

}
