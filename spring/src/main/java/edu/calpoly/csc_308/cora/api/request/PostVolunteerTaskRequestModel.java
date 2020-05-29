package edu.calpoly.csc_308.cora.api.request;

public class PostVolunteerTaskRequestModel implements RequestModel {
  public String name;
  public String location;
  public Integer need;
  public String description;
  public Long ownerId;
  public Long photoId;
}
