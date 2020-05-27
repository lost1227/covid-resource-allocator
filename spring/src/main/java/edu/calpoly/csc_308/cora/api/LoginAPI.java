package edu.calpoly.csc_308.cora.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;

import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.security.AuthUser;

@RestController
public class LoginAPI {

    private Logger logger = LoggerFactory.getLogger(MessengerAPI.class);

    @GetMapping("/api/login")
    public ResponseModel login(Authentication authentication) {
        User principal = ((AuthUser) authentication.getPrincipal()).user;
        return new UserInfoResponse(principal.id, principal.name, principal.location, principal.userType, principal.description, principal.skillSet);
    }
}
