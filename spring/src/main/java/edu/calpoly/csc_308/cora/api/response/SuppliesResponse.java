package edu.calpoly.csc_308.cora.api.response;

import java.util.List;

import edu.calpoly.csc_308.cora.entities.Supply;
import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SuppliesResponse extends ResponseModel {

  @Data
  @EqualsAndHashCode(callSuper=true)
  public static class SupplyResponse extends ResponseModel {
    private final Long id;

    private final String name;
    private final String location;
    
    private final Integer need;

    private final String description;

    private final Long ownerId;
    
    private final SupplyType type;

    private final Integer quantity;

    private final Long photoId;

    public static SupplyResponse fromSupply(Supply supply) {
      return new SupplyResponse(
        supply.getId(),
        supply.getName(), 
        supply.getLocation(), 
        supply.getNeed(),
        supply.getDescription(),
        supply.getOwnerId(),
        supply.getType(),
        supply.getQuantity(),
        supply.getPhotoId());
    }
  }

  private final List<SupplyResponse> supplies;
    
}
