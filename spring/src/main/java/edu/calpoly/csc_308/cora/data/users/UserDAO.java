package edu.calpoly.csc_308.cora.data.users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserDAO {
    public @Id @GeneratedValue Long id;

    public String name;

    public String location;
    public String userType;
    public String description;
    public String[] skillSet;

    public Long photoId;

    @Column(unique = true)
    public String username;
    public String passwordHash;

    public UserDAO() {}

    public UserDAO(
        String name,
        String location,
        String userType,
        String description,
        String[] skillSet,

        Long photoId,

        String username, 
        String passwordHash
        ) {
        this.name = name;
        this.location = location;
        this.userType = userType;
        this.description = description;
        this.skillSet = skillSet;

        this.photoId = photoId;

        this.username = username;
        this.passwordHash = passwordHash;
    }
}
