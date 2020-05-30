package edu.calpoly.csc_308.cora.security;

import java.util.Optional;

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
        AuthUser authUser = new AuthUser(
            dao.username,
            dao.passwordHash,
            User.fromDao(dao)
        );
        return authUser;
    }
    

    public AuthUser registerNewUser(User user, String username, String password) {
      String hashedPassword = encoder.encode(password);
      UserDAO dao = new UserDAO(user.name, user.location, user.userType, user.description, user.skillSet, user.photoId, username, hashedPassword);

      UserDAO preexisting = users.findByUsername(username);
      if(preexisting != null) {
        throw new IllegalArgumentException("User already exists!");
      }

      dao = users.save(dao);
      User ret = User.fromDao(dao);
      return new AuthUser(username, hashedPassword, ret);
    }

    public AuthUser updateUser(AuthUser principal, User user, String password) {
      Optional<UserDAO> opDao = users.findById(principal.user.id);
      if(!opDao.isPresent()) {
        throw new IllegalArgumentException("Principal does not exist!");
      }
      UserDAO dao = opDao.get();
      if(!user.name.isEmpty())
        dao.name = user.name;
      if(!user.photoId.equals(-1L))
        dao.photoId = user.photoId;
      if(!user.location.isEmpty())
        dao.location = user.location;
      if(!user.description.isEmpty())
        dao.description = user.description;
      if(user.skillSet.length > 0)
        dao.skillSet = user.skillSet;
      if(!password.isEmpty())
        dao.passwordHash = encoder.encode(password);

      dao = users.save(dao);

      return new AuthUser(dao.username, dao.passwordHash, User.fromDao(dao));
    }
    
}
