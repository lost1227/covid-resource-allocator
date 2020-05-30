package edu.calpoly.csc_308.cora.api.request;

public class NewUserRequestModel implements RequestModel {
  public Long id;
  public String name;
  public String location;
  public String userType;
  public String description;
  public String[] skillset;

  public String username;
  public String password;
}
