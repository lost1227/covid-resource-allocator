package edu.calpoly.csc_308.cora.api.request;

import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

public class SuppliesFilterRequestModel {
    public String[] enabledFilters;

    public SupplyType type;
    public Integer need;
    public String location;

    public String search;
}
