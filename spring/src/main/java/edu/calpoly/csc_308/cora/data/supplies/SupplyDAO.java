package edu.calpoly.csc_308.cora.data.supplies;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class SupplyDAO {
    private @Id @GeneratedValue Long id;
    
    private String name;
    private String location;
    
    private Integer need;

    private String description;

    private long ownerId;
    private SupplyType type;
    private Integer quantity;

    private Long photoId;

    public SupplyDAO(String name, String location, Integer need, String description, long ownerId, SupplyType type, Integer quantity, Long photoId) {
        this.name = name;
        this.location = location;
        this.need = need;
        this.description = description;
        this.ownerId = ownerId;
        this.type = type;
        this.quantity = quantity;
        this.photoId = photoId;
    }
}
