package edu.calpoly.csc_308.cora.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VolunteerFilterRequestModel {

  private String[] enabledFilters = {};

  private String[] skillSet = {};
  private Integer need = 0;
  private String location = "";
  private String search = "";

}
