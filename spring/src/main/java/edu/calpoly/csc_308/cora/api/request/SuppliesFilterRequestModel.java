package edu.calpoly.csc_308.cora.api.request;

import edu.calpoly.csc_308.cora.data.supplies.SupplyType;

public class SuppliesFilterRequestModel {
    public String[] enabledFilters;

    public SupplyType type;
    public Integer need;
    public Integer locationDistance;
}
