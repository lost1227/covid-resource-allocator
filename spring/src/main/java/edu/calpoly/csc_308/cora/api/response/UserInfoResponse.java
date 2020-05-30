package edu.calpoly.csc_308.cora.api.response;

import edu.calpoly.csc_308.cora.entities.User;

public class UserInfoResponse extends ResponseModel {
  public Long id;
  public String name;
  public String location;
  public String userType;
  public String description;
  public String[] skillset;
  public Long photoId;

  public UserInfoResponse(Long id, String name, String location, String userType, String description, String[] skillset, Long photoId) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.userType = userType;
    this.description = description;
    this.skillset = skillset;
    this.photoId = photoId;
  }

  public static UserInfoResponse fromUser(User user) {
    return new UserInfoResponse(
      user.getId(),
      user.getName(),
      user.getLocation(),
      user.getUserType(),
      user.getDescription(),
      user.getSkillSet(), 
      user.getPhotoId());
  }
}
