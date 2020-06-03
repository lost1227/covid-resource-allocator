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
        return new AuthUser(
          new AuthUser.Auth(
            dao.getUsername(),
            dao.getPasswordHash()),
          User.fromDao(dao)
        );
    }
    

    public AuthUser registerNewUser(User user, String username, String password) {
      String hashedPassword = encoder.encode(password);
      
      UserDAO.ProfileInfo info = new UserDAO.ProfileInfo(user.getName(), user.getLocation(), user.getUserType(), user.getDescription(), user.getSkillSet());
      UserDAO dao = new UserDAO(info, user.getPhotoId(), username, hashedPassword);

      UserDAO preexisting = users.findByUsername(username);
      if(preexisting != null) {
        throw new IllegalArgumentException("User already exists!");
      }

      dao = users.save(dao);
      User ret = User.fromDao(dao);
      return new AuthUser(new AuthUser.Auth(username, hashedPassword), ret);
    }

    public AuthUser updateUser(AuthUser principal, User user, String password) {
      Optional<UserDAO> opDao = users.findById(principal.getUser().getId());
      if(!opDao.isPresent()) {
        throw new IllegalArgumentException("Principal does not exist!");
      }
      UserDAO dao = opDao.get();
      if(!user.getName().isEmpty())
        dao.setName(user.getName());
      if(!user.getPhotoId().equals(-1L))
        dao.setPhotoId(user.getPhotoId());
      if(!user.getLocation().isEmpty())
        dao.setLocation(user.getLocation());
      if(!user.getDescription().isEmpty())
        dao.setDescription(user.getDescription());
      if(user.getSkillSet().length > 0)
        dao.setSkillSet(user.getSkillSet());
      if(!password.isEmpty())
        dao.setPasswordHash(encoder.encode(password));

      dao = users.save(dao);

      return new AuthUser(new AuthUser.Auth(dao.getUsername(), dao.getPasswordHash()), User.fromDao(dao));
    }
    
}
