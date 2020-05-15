package edu.calpoly.csc_308.cora.services;

import java.util.List;
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
        UserDAO dao = repo.findById(id).get();
        User user = new User(dao.id, dao.name, dao.location, dao.userType, dao.description, dao.skillSet);

        return user;
    }

    public List<User> findUsersByName(String name) {
        List<UserDAO> daos = repo.findByName(name);
        List<User> users = daos.stream()
                                .map(dao -> new User(dao.id, dao.name, dao.location, dao.userType, dao.description, dao.skillSet))
                                .collect(Collectors.toList());
        return users;
    }

}
