package edu.calpoly.csc_308.cora.api.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class VolunteerTasksResponse extends ResponseModel {

  @Data
  @EqualsAndHashCode(callSuper=true)
  public static class VolunteerTaskResponse extends ResponseModel {
    private final Long id;

    private final String name;
    private final String location;
    
    private final Integer need;

    private final String description;

    private final Long ownerId;

    private final String skillNeeded;

    private final Long photoId;
  }

  private final List<VolunteerTaskResponse> tasks;

}
