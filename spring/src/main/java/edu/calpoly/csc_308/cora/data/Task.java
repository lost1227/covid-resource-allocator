package edu.calpoly.csc_308.cora.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Task {
    public @Id @GeneratedValue Long id;
    
    public String name;
    public String location;
    public String description;

    public Task() {};

    public Task(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }
}
