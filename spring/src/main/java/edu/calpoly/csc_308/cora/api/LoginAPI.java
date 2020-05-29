package edu.calpoly.csc_308.cora.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.core.Authentication;

import edu.calpoly.csc_308.cora.api.request.NewUserRequestModel;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.SuccessResponse;
import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.security.AuthUser;
import edu.calpoly.csc_308.cora.security.AuthUserService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class LoginAPI {

    private Logger logger = LoggerFactory.getLogger(MessengerAPI.class);

    @Autowired
    private AuthUserService userSerivce;

    @GetMapping("/api/login")
    public ResponseModel login(Authentication authentication) {
        User principal = ((AuthUser) authentication.getPrincipal()).user;
        return new UserInfoResponse(principal.id, principal.name, principal.location, principal.userType, principal.description, principal.skillSet);
    }

    @PostMapping("/api/login/register") 
    public ResponseModel registerUser(@RequestBody NewUserRequestModel request) {
      logger.info("Registering user {}", request.username);
      User user = new User(null, request.name, request.location, request.userType, request.description, request.skillset);
      userSerivce.registerNewUser(user, request.username, request.password);
      return new SuccessResponse();
    }
}
