package edu.calpoly.csc_308.cora.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Service extends ResourcePost {
    public String availability;

    public Service(String name, String location, String description, String taskOwner, String availability){
        super(name, location, description, taskOwner);
        this.availability = availability;
    }
}
