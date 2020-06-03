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

    @Data
    public static class TaskProfile {
      private final String name;
      private final String location;
      private final String description;
      private final String instructions;
    }
    
    private String name;
    private String location;
    
    private Integer need;

    private String description;
    private String instructions;

    private Long ownerId;

    private Long photoId;

    private String skillNeeded;

    public VolunteerTaskDAO(TaskProfile profile, Integer need, Long ownerId, String skillNeeded, Long photoId) {
        this.name = profile.name;
        this.location = profile.location;
        this.instructions = profile.instructions;
        this.need = need;
        this.description = profile.description;
        this.ownerId = ownerId;
        this.skillNeeded = skillNeeded;     
        this.photoId = photoId;
    }
}
