package edu.calpoly.csc_308.cora.entities;

import lombok.Data;

@Data
public class Supply {

  public static enum SupplyType {
    REQUEST,
    OFFER
  }


    public Long id;
    
    public String name;
    public String location;
    
    public Integer need;

    public String description;

    public Long ownerId;
    public SupplyType type;
    public Integer quantity;

    public Long photoId;

    public Long acceptorId;

    public Supply(Long id, String name, String location, Integer need, String description, Long ownerId, SupplyType type, Integer quantity, Long photoId, Long acceptorId) {
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
