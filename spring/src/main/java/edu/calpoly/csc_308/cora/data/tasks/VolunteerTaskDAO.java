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

    public Long ownerId;

    public Long photoId;

    public VolunteerTaskDAO() {};

    public VolunteerTaskDAO(String name, String location, Integer need, String description, Long ownerId, Long photoId) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.ownerId = ownerId;
        this.photoId = photoId;
    }
}
