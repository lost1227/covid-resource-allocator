package edu.calpoly.csc_308.cora.entities;

import lombok.Data;

@Data
public class Supply {

  public static enum SupplyType {
    REQUEST,
    OFFER
  }


  private final Long id;
  
  private final String name;
  private final String location;
  
  private final Integer need;

  private final String description;

  private final Long ownerId;
  private final SupplyType type;
  private final Integer quantity;

  private final Long photoId;
}
