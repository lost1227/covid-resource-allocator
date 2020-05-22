package edu.calpoly.csc_308.cora.entities;

import edu.calpoly.csc_308.cora.data.supplies.SupplyType;
import lombok.Data;

@Data
public class Supply {
    
    public String name;
    public String location;
    
    public Integer need;

    public String description;

    public long taskOwnerId;
    public SupplyType type;
    public int quantity;

    public Supply(String name, String location, Integer need, String description, long taskOwnerId, SupplyType type, int quantity) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwnerId = taskOwnerId;
        this.type=type;
        this.quantity=quantity;
    }
}
