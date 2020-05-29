package edu.calpoly.csc_308.cora.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import edu.calpoly.csc_308.cora.data.users.UserDAO;
import edu.calpoly.csc_308.cora.data.users.UserRepository;
import edu.calpoly.csc_308.cora.entities.User;

import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthUserService implements UserDetailsService {

    
    Logger logger = LoggerFactory.getLogger(AuthUserService.class);

    @Autowired
    private UserRepository users;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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
            new User(dao.id, dao.name, dao.location, dao.userType, dao.description, dao.skillSet)
        );
        return authUser;
    }
    

    public AuthUser registerNewUser(User user, String username, String password) {
      String hashedPassword = encoder.encode(password);
      UserDAO dao = new UserDAO(user.name, user.location, user.userType, user.description, user.skillSet, username, hashedPassword);

      UserDAO preexisting = users.findByUsername(username);
      if(preexisting != null) {
        throw new IllegalArgumentException("User already exists!");
      }

      dao = users.save(dao);
      User ret = new User(dao.id, dao.name, dao.location, dao.userType, dao.description, dao.skillSet);
      return new AuthUser(username, hashedPassword, ret);
    }
    
}
