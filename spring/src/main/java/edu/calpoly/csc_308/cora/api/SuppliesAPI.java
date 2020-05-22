package edu.calpoly.csc_308.cora.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse.SupplyResponse;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SuppliesAPI {

    @Autowired
    private SupplyRepository suppRepo;
    
    @PostMapping("/api/supplies")
    public ResponseModel getSupplies(@RequestBody SuppliesFilterRequestModel request) {
        // TODO: add filter logic

        List<SupplyResponse> supplies = suppRepo.findAll().stream().map(
                dao -> new SupplyResponse(dao.id, dao.name, dao.location, dao.need, dao.description, dao.taskOwnerId, dao.type, dao.quantity)
            ).collect(Collectors.toList());
            
        return new SuppliesResponse(supplies);
    }

}
