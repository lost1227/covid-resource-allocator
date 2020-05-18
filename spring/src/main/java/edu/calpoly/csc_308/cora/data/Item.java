package edu.calpoly.csc_308.cora.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Item extends ResourcePost {
    public Integer quantity;

    public Item(String name, String location, String description, String taskOwner, Integer quantity){
        super(name, location, description, taskOwner);
        this.quantity = quantity;
    }
}
