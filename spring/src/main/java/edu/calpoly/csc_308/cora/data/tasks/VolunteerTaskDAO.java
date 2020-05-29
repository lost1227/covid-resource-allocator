package edu.calpoly.csc_308.cora.data.tasks;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity

public class VolunteerTaskDAO {
    
    public @Id @GeneratedValue Long id;
    
    public String name;
    public String location;
    
    public Integer need;

    public String description;

    public Long taskOwnerId;

    public VolunteerTaskDAO() {};

    public String skillsNeeded;

    public VolunteerTaskDAO(String name, String location, Integer need, String description, Long taskOwnerId, String skillsNeeded) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwnerId = taskOwnerId;
        this.skillsNeeded = skillsNeeded;
    }
}
