package edu.calpoly.csc_308.cora.api.request;

import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuppliesFilterRequestModel {
    private String[] enabledFilters;

    private SupplyType type;
    private Integer need;
    private String location;

    private String search;
}
