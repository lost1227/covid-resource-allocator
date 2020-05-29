package edu.calpoly.csc_308.cora.api.request;

import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;


public class PostSupplyRequestModel implements RequestModel {
  public String name;
  public String location;
  public Integer need;
  public String description;
  public Long ownerId;
  public SupplyType type;
  public Integer quantity;
  public Long photoId;
}
