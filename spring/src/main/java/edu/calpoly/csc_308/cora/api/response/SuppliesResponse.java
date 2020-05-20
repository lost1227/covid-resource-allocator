package edu.calpoly.csc_308.cora.api.response;

import java.util.List;

import edu.calpoly.csc_308.cora.data.supplies.SupplyType;

public class SuppliesResponse extends ResponseModel {
    public static class SupplyResponse {
        public Long id;

        public String name;
        public String location;
        
        public Integer need;

        public String description;

        public Long ownerId;
        
        public SupplyType type;

        public Integer quantity;

        public SupplyResponse(Long id, String name, String location, Integer need, String description, Long ownerId, SupplyType type, Integer quantity) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.need = need;
            this.description = description;
            this.ownerId = ownerId;
            this.type = type;
            this.quantity = quantity;
        }
    }

    public List<SupplyResponse> supplies;

    public SuppliesResponse(List<SupplyResponse> supplies) {
        this.supplies = supplies;
    }

    public SuppliesResponse() {
    }
    
}
