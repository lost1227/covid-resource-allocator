package edu.calpoly.csc_308.cora.data.supplies;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    public int quantity;

    public SupplyDAO() {}

    public SupplyDAO(String name, String location, Integer need, String description, long taskOwnerId, SupplyType type, int quantity) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwnerId = taskOwnerId;
        this.type=type;
        this.quantity=quantity;
    }
}
