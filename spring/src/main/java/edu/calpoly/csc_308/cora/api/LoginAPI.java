package edu.calpoly.csc_308.cora.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.core.Authentication;

import edu.calpoly.csc_308.cora.api.request.EditUserRequestModel;
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

    @Autowired
    private AuthUserService userSerivce;

    @GetMapping("/api/login")
    public ResponseModel login(Authentication authentication) {
        User principal = ((AuthUser) authentication.getPrincipal()).getUser();
        return new UserInfoResponse(principal.getId(), principal.getName(), principal.getLocation(), principal.getUserType(), principal.getDescription(), principal.getSkillSet(), principal.getPhotoId());
    }

    @PostMapping("/api/login/register") 
    public ResponseModel registerUser(@RequestBody NewUserRequestModel request) {
      User user = new User(null, request.getName(), request.getLocation(), request.getUserType(), request.getDescription(), request.getSkillset(), -1L);
      userSerivce.registerNewUser(user, request.getUsername(), request.getPassword());
      return new SuccessResponse();
    }

    @PostMapping("/api/login/edit")
    public ResponseModel editUserInfo(Authentication authentication, @RequestBody EditUserRequestModel request) {
      AuthUser principal = ((AuthUser) authentication.getPrincipal());
      User user = new User(principal.getUser().getId(), request.getName(), request.getLocation(), principal.getUser().getUserType(), request.getDescription(), request.getSkillset(), request.getPhotoId());
      AuthUser result = userSerivce.updateUser(principal, user, request.getPassword());
      
      principal.setUser(result.getUser());

      return UserInfoResponse.fromUser(result.getUser());
    }
}
