package edu.calpoly.csc_308.cora.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.calpoly.csc_308.cora.data.users.UserDAO;
import edu.calpoly.csc_308.cora.data.users.UserRepository;
import edu.calpoly.csc_308.cora.entities.User;

@Service
public class UserManager {
    
    private UserRepository repo;

    public UserManager(UserRepository repo) {
        this.repo = repo;
    }

    public User getUser(Long id) {
        Optional<UserDAO> opDao = repo.findById(id);
        if(!opDao.isPresent()) {
          return null;
        }
        UserDAO dao = opDao.get();
        User user = User.fromDao(dao);

        return user;
    }

    public List<User> findUsersByName(String name) {
        List<UserDAO> daos = repo.findByName(name);
        List<User> users = daos.stream()
                                .map(dao -> User.fromDao(dao))
                                .collect(Collectors.toList());
        return users;
    }

}
