package edu.calpoly.csc_308.cora.entities;

//import javax.persistence.Entity;

import lombok.Data;

//@Entity
@Data
public class User {
    public final Long id;    
    public final String name;

    public final String location;
    public final String userType;
    public final String description;
    public final String[] skillSet;

    public User(Long id, String name, String location, String userType, String description, String[] skillSet) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.userType = userType;
        this.description = description;
        this.skillSet = skillSet;
    }
}
