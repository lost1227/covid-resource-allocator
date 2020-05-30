package edu.calpoly.csc_308.cora.data.supplies;

import java.util.List;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;

public interface SupplyRepositoryCustom {

  List<SupplyDAO> suppliesByFilters(SuppliesFilterRequestModel filters);
  
}
