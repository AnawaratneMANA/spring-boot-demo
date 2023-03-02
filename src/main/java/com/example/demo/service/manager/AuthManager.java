package com.example.demo.service.manager;

import com.example.demo.provider.KeyclockAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthManager {
    @Autowired
    KeyclockAuthProvider keyclockAuthProvider;

    public void keycloakUserRegistration(){
        keyclockAuthProvider.retriveKeyCloakAgents();
    }
}
