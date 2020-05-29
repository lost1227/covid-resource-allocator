package edu.calpoly.csc_308.cora.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseModel getUserInfo(@RequestParam Long id) {

        User user = userManager.getUser(id);
        UserInfoResponse response = new UserInfoResponse(user.id, user.name, user.location, user.userType, user.description, user.skillSet, user.photoId);

        return response;
    }

    @GetMapping("/api/user/find")
    public ResponseModel findUserByName(@RequestParam String name) {
        List<User> users = userManager.findUsersByName(name);
        List<UserInfoResponse> responses = users.stream()
                                                .map(user -> new UserInfoResponse(user.id, user.name, user.location, user.userType, user.description, user.skillSet, user.photoId))
                                                .collect(Collectors.toList());
        FindUsersResponse response = new FindUsersResponse(name, responses);
        return response;
    }
}
