package edu.calpoly.csc_308.cora.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Supply {
    public @Id @GeneratedValue Long id;
    
    public String name;
    public String location;
    
    public Integer need;

    public String description;

    public String taskOwner;

    public Supply() {};

    public Supply(String name, String location, Integer need, String description, String taskOwner) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwner = taskOwner;
    }
}