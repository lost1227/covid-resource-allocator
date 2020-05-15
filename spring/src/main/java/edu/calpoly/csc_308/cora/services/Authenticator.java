package edu.calpoly.csc_308.cora.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Authenticator {

    private HashMap<String, Long> loggedinUsers = new HashMap<>();

    public Authenticator() {
        // TODO: Implement authentication logic
        loggedinUsers.put("abc123", 2L);
    }

    public Long getUIDforKey(String key) {
        return loggedinUsers.get(key);
    }
    
}
