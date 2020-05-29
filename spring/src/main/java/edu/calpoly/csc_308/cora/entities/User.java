package edu.calpoly.csc_308.cora.entities;

import edu.calpoly.csc_308.cora.data.users.UserDAO;
import lombok.Data;

@Data
public class User {
    public final Long id;    
    public final String name;

    public final Long photoId;

    public final String location;
    public final String userType;
    public final String description;
    public final String[] skillSet;

    public User(Long id, String name, String location, String userType, String description, String[] skillSet, Long photoId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.userType = userType;
        this.description = description;
        this.skillSet = skillSet;
        this.photoId = photoId;
    }


    public static User fromDao(UserDAO dao) {
      return new User(dao.id, dao.name, dao.location, dao.userType, dao.description, dao.skillSet, dao.photoId);
    }
}
