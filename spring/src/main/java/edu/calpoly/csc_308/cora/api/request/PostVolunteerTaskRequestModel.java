package edu.calpoly.csc_308.cora.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVolunteerTaskRequestModel implements RequestModel {
  private String name;
  private String location;
  private Integer need;
  private String description;
  private Long ownerId;
  private String skillNeeded;
  private Long photoId;
}
