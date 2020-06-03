package edu.calpoly.csc_308.cora.entities;

import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import lombok.Data;

@Data
public class Supply {

  public enum SupplyType {
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

  public static Supply fromDAO(SupplyDAO dao) {
    return new Supply(dao.getId(), dao.getName(), dao.getLocation(), dao.getNeed(), dao.getDescription(), dao.getOwnerId(), dao.getType(), dao.getQuantity(), dao.getPhotoId());
  }
}
