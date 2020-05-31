package edu.calpoly.csc_308.cora.entities;

import edu.calpoly.csc_308.cora.data.users.UserDAO;
import lombok.Data;

@Data
public class User {
  private final Long id;    
  private final String name;

  private final String location;
  private final String userType;
  private final String description;
  private final String[] skillSet;

  private final Long photoId;

  public static User fromDao(UserDAO dao) {
    return new User(dao.getId(), dao.getName(), dao.getLocation(), dao.getUserType(), dao.getDescription(), dao.getSkillSet(), dao.getPhotoId());
  }
}
