package edu.calpoly.csc_308.cora.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewUserRequestModel implements RequestModel {
  private Long id;
  private String name;
  private String location;
  private String userType;
  private String description;
  private String[] skillset;

  private String username;
  private String password;
}
