package edu.calpoly.csc_308.cora.data.supplies;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Data
@Entity
public class SupplyDAO {
    public @Id @GeneratedValue Long id;
    
    public String name;
    public String location;
    
    public Integer need;

    public String description;

    public Long taskOwnerId;

    @Enumerated(EnumType.STRING)
    public SupplyType type;

    public Integer quantity;

    public SupplyDAO() {};

    public SupplyDAO(String name, String location, Integer need, String description, Long taskOwnerId, SupplyType type, Integer quantity) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.taskOwnerId = taskOwnerId;
        this.type = type;
        this.quantity = quantity;
    }
}
