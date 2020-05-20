package edu.calpoly.csc_308.cora.entities;

import lombok.Data;

@Data
public class Supply {
    
    public String name;
    public String location;
    
    public Integer need;

    public String description;

    public String taskOwner;

    public Supply(String name, String location, Integer need, String description, String taskOwner) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwner = taskOwner;
    }
}
