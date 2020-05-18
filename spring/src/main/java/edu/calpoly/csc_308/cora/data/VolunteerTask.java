package edu.calpoly.csc_308.cora.data;

import java.util.List;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class VolunteerTask {
    public @Id @GeneratedValue Long id;
    
    public String name;
    public String location;

    public String[] skillsNeeded;
    
    public Integer need;

    public String description;

    public String taskOwner;

    public String avatar;

    public String img;

    public List<Volunteer> volunteers;

    public VolunteerTask() {};

    public VolunteerTask(String name, String location, Integer need, String description, String taskOwner, String skills) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwner = taskOwner;
        this.skillsNeeded = skills.split("[ ,]+");
    }
}
