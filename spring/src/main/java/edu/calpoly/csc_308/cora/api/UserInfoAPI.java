package edu.calpoly.csc_308.cora.api;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.api.response.FailResponse;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;

@RestController
public class UserInfoAPI {

    Logger logger = LoggerFactory.getLogger(UserInfoAPI.class);

    private HashMap<Long, ResponseModel> userInfoStubDB = new HashMap<>();

    public UserInfoAPI() {
        userInfoStubDB.put(0L, new UserInfoResponse(0L, "Memorialcare Health System", "Long Beach, CA", "provider", "Hospital located in Long Beach, California", "n/a"));
        userInfoStubDB.put(1L, new UserInfoResponse(1L, "Blue Shield of California", "Long Beach, CA", "provider", "Insurance company serving Long Beach, California", "n/a"));
        userInfoStubDB.put(2L, new UserInfoResponse(2L, "Jordan Powers", "Long Beach, CA", "volunteer", "Student living in Long Beach, CA", "programming"));
    }

    @GetMapping("/api/user/info")
    public ResponseModel getUserInfo(@RequestParam Long id) {
        return userInfoStubDB.getOrDefault(id, new FailResponse());
    }
}
