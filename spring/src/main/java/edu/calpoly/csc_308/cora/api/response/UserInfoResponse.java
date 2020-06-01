package edu.calpoly.csc_308.cora.api.response;

import edu.calpoly.csc_308.cora.entities.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserInfoResponse extends ResponseModel {
  private final Long id;
  private final String name;
  private final String location;
  private final String userType;
  private final String description;
  private final String[] skillset;
  private final Long photoId;

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
