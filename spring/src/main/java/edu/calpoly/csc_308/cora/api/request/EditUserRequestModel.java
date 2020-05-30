package edu.calpoly.csc_308.cora.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditUserRequestModel implements RequestModel {
  private String name;
  private String location;
  private String description;
  private String[] skillset;
  private String password;
  private Long photoId;
}
