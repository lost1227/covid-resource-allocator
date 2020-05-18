package edu.calpoly.csc_308.cora.data;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ResourcePost {
    public @Id @GeneratedValue Long id;

    public String title;
    public String location;
    public String description;
    public String taskOwner;

    public ResourcePost() {};

    public ResourcePost(String title, String location, String description, String taskOwner) {
        this.title=title;
        this.location=location;
        this.description=description;
        this.taskOwner=taskOwner;
    }
}
