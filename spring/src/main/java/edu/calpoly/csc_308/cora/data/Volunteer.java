package edu.calpoly.csc_308.cora.data;

import java.util.List;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity

public class Volunteer{
    public String name;
    public String location;
    public String[] skillset;
    public List<VolunteerTask> currentTasks;

    public Volunteer(String name, String location, String[] skillset){
        this.name = name;
        this.location = location;
        this.skillset = skillset;
    }
}