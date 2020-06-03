package edu.calpoly.csc_308.cora.api.request;

import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostSupplyRequestModel implements RequestModel {
  private String name;
  private String location;
  private Integer need;
  private String description;
  private Long ownerId;
  private SupplyType type;
  private Integer quantity;
  private Long photoId;
}
