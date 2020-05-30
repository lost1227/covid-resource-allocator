package edu.calpoly.csc_308.cora.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import edu.calpoly.csc_308.cora.api.response.FailResponse;
import edu.calpoly.csc_308.cora.api.response.FindUsersResponse;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.services.UserManager;

@RestController
public class UserInfoAPI {

    Logger logger = LoggerFactory.getLogger(UserInfoAPI.class);

    UserManager userManager;

    public UserInfoAPI(UserManager users) {
        this.userManager = users;
    }

    @GetMapping("/api/user/info")
    public ResponseEntity<ResponseModel> getUserInfo(@RequestParam Long id) {
        User user = userManager.getUser(id);
        if(user == null) {
          return ResponseEntity.badRequest().body(new FailResponse());
        }

        UserInfoResponse response = UserInfoResponse.fromUser(user);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/user/find")
    public ResponseModel findUserByName(@RequestParam String name) {
        List<User> users = userManager.findUsersByName(name);
        List<UserInfoResponse> responses = users.stream()
                                                .map(UserInfoResponse::fromUser)
                                                .collect(Collectors.toList());
        return new FindUsersResponse(name, responses);
    }
}
