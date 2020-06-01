package edu.calpoly.csc_308.cora.data.tasks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class VolunteerTaskDAO {
    
    private @Id @GeneratedValue Long id;
    
    private String name;
    private String location;
    
    private Integer need;

    private String description;

    private Long ownerId;

    private Long photoId;

    private String skillNeeded;

    public VolunteerTaskDAO(String name, String location, Integer need, String description, Long ownerId, String skillNeeded, Long photoId) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.ownerId = ownerId;
        this.skillNeeded = skillNeeded;     
        this.photoId = photoId;
    }
}
