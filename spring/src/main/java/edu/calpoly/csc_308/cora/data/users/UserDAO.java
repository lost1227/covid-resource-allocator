package edu.calpoly.csc_308.cora.data.users;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UserDAO {
  private @Id @GeneratedValue Long id;

  private String name;

  private String location;
  private String userType;
  private String description;
  private String[] skillSet;

  private Long photoId;

  @Column(unique = true)
  private String username;
  private String passwordHash;

  @Data
  public static class ProfileInfo {
    private final String name;
    private final String location;
    private final String userType;
    private final String description;
    private final String[] skillSet;
  }


  public UserDAO(ProfileInfo profile, Long photoId, String username, String passwordHash) {
    this.name = profile.getName();
    this.location = profile.getLocation();
    this.userType = profile.getUserType();
    this.description = profile.getDescription();
    this.skillSet = profile.getSkillSet();

    this.photoId = photoId;

    this.username = username;
    this.passwordHash = passwordHash;
  }
}
