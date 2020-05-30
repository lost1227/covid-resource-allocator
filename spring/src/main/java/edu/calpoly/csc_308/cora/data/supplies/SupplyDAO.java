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

    public long ownerId;
    public SupplyType type;
    public Integer quantity;

    public Long photoId;

    public Long acceptorId;

    public SupplyDAO() {}

    public SupplyDAO(String name, String location, Integer need, String description, long ownerId, SupplyType type, Integer quantity, Long photoId, Long acceptorId) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.ownerId = ownerId;
        this.type = type;
        this.quantity = quantity;
        this.photoId = photoId;
        this.acceptorId = acceptorId;
    }
}
