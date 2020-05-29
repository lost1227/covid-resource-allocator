package edu.calpoly.csc_308.cora.data.supplies;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

@Data
@Entity

public class SupplyDAO {
    public @Id @GeneratedValue Long id;
    
    public String name;
    public String location;
    
    public Integer need;

    public String description;

    public long taskOwnerId;
    public SupplyType type;
    public Integer quantity;

    public Long photoId;

    public SupplyDAO() {}

    public SupplyDAO(String name, String location, Integer need, String description, long taskOwnerId, SupplyType type, Integer quantity, Long photoId) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwnerId = taskOwnerId;
        this.type = type;
        this.quantity = quantity;
        this.photoId = photoId;
    }
}
