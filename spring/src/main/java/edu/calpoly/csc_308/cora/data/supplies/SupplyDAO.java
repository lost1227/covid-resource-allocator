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

    @Data
    public static class DescInfo {
      private final String name;
      private final String location;
      private final String description;
    }
    
    private String name;
    private String location;
    
    private Integer need;

    private String description;

    private long ownerId;
    private SupplyType type;
    private Integer quantity;

    private Long photoId;

    public SupplyDAO(DescInfo description, Integer need, long ownerId, SupplyType type, Integer quantity, Long photoId) {
        this.name = description.getName();
        this.location = description.getLocation();
        this.need = need;
        this.description = description.getDescription();
        this.ownerId = ownerId;
        this.type = type;
        this.quantity = quantity;
        this.photoId = photoId;
    }
}
