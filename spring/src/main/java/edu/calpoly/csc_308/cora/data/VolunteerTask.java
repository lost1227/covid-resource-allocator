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

<<<<<<< HEAD:spring/src/main/java/edu/calpoly/csc_308/cora/data/VolunteerTask.java
    public String avatar;

    public String img;

    public List<Volunteer> volunteers;

    public VolunteerTask() {};

    public VolunteerTask(String name, String location, Integer need, String description, String taskOwner, String skills) {
=======
    public String[] skillsNeeded;

    public VolunteerTask() {};

    public VolunteerTask(String name, String location, Integer need, String description, String taskOwner, String[] skills) {
>>>>>>> 788c63d... Updated ViewTasks:spring/src/main/java/edu/calpoly/csc_308/cora/data/tasks/VolunteerTask.java
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwner = taskOwner;
<<<<<<< HEAD:spring/src/main/java/edu/calpoly/csc_308/cora/data/VolunteerTask.java
        this.skillsNeeded = skills.split("[ ,]+");
=======
        this.skillsNeeded = skills;
>>>>>>> 788c63d... Updated ViewTasks:spring/src/main/java/edu/calpoly/csc_308/cora/data/tasks/VolunteerTask.java
    }
}
