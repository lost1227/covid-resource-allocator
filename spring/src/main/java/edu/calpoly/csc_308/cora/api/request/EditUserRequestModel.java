package edu.calpoly.csc_308.cora.api.request;

public class EditUserRequestModel implements RequestModel {
  public String name;
  public String location;
  public String description;
  public String[] skillset;
  public String password;
  public Long photoId;
}
