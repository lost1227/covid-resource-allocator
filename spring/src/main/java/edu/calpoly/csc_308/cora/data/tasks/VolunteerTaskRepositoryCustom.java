package edu.calpoly.csc_308.cora.data.tasks;

import java.util.List;

import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;

public interface VolunteerTaskRepositoryCustom {
  
  List<VolunteerTaskDAO> tasksByFilters(VolunteerFilterRequestModel filters);

}
