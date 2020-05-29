package edu.calpoly.csc_308.cora.api.request;

import edu.calpoly.csc_308.cora.data.supplies.SupplyType;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//match with exactly what is in DAO
public class PostSupplyRequestModel implements RequestModel {
    public String name;
    public String location;
    public Integer need;
    public String description;
    public long taskOwnerId;
    public SupplyType type;
    public int quantity;

}
