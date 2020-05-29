package edu.calpoly.csc_308.cora.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;
import edu.calpoly.csc_308.cora.api.request.PostSupplyRequestModel;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.SuccessResponse;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse.SupplyResponse;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.Supply;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.security.AuthUser;
import edu.calpoly.csc_308.cora.services.SupplyManager;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SuppliesAPI {

    private Logger logger = LoggerFactory.getLogger(SuppliesAPI.class);
    private SupplyManager supp;

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
    //create @PostMapping ResponseModel 
    //package SupplyDAO
    //call post supply
    @PostMapping("/api/post/supply")
    public ResponseModel postSupplies(@RequestBody PostSupplyRequestModel postRequest, Authentication authentication){
        logger.info("postSupply: {}", postRequest);

        logger.info("principal: {}", authentication.getPrincipal());

        Supply supply = new Supply(postRequest.name,postRequest.location,postRequest.need,postRequest.description,postRequest.taskOwnerId,postRequest.type,postRequest.quantity);
        supp.postSupply(supply);
        return new SuccessResponse();
    }

}
