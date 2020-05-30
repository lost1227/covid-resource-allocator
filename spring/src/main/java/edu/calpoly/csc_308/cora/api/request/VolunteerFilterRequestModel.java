package edu.calpoly.csc_308.cora.api.request;

import lombok.Data;

@Data
public class VolunteerFilterRequestModel {

  public String[] enabledFilters;

  public String[] skillSet;
  public Integer need;
  public String location;
  public String search;

}
