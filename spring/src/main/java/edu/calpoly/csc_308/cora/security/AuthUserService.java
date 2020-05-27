package edu.calpoly.csc_308.cora.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import edu.calpoly.csc_308.cora.data.users.UserDAO;
import edu.calpoly.csc_308.cora.data.users.UserRepository;
import edu.calpoly.csc_308.cora.entities.User;

import org.springframework.security.core.userdetails.UserDetails;

@Service
public class AuthUserService implements UserDetailsService {

    
    Logger logger = LoggerFactory.getLogger(AuthUserService.class);

    @Autowired
    private UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDAO dao = users.findByUsername(username);
        if(dao == null) {
            throw new IllegalArgumentException("No such user " + username);
        }
        logger.info("Authenticating user "+username);
        AuthUser authUser = new AuthUser(
            dao.username,
            dao.passwordHash,
            true,
            true,
            true,
            true,
            new ArrayList<>(),
            new User(dao.id, dao.name, dao.location, dao.userType, dao.description, dao.skillSet)
        );
        return authUser;
    }
    
}
