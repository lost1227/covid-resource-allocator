package edu.calpoly.csc_308.cora.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;
import edu.calpoly.csc_308.cora.api.request.PostSupplyRequestModel;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse.SupplyResponse;
import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.Supply;
import edu.calpoly.csc_308.cora.services.SupplyManager;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SuppliesAPI {

  @Autowired
  private SupplyRepository suppRepo;

  @Autowired
  private SupplyManager supp;
  
  @PostMapping("/api/supplies")
  public ResponseModel getSupplies(@RequestBody SuppliesFilterRequestModel request) {

    List<SupplyDAO> daos;
    if(request.enabledFilters.length > 0) {
      daos = suppRepo.suppliesByFilters(request);
    } else {
      daos = suppRepo.findAll();
    }


    List<SupplyResponse> supplies = daos.stream().map(
      dao -> new SupplyResponse(dao.id, dao.name, dao.location, dao.need, dao.description, dao.ownerId, dao.type, dao.quantity, dao.photoId, dao.acceptorId)
    ).collect(Collectors.toList());
    
    return new SuppliesResponse(supplies);
  }

  @GetMapping("/api/supply")
  public ResponseModel getSingleSupply(@RequestParam Long id) {
    SupplyDAO dao = suppRepo.findById(id).get();
    return new SupplyResponse(dao.id, dao.name, dao.location, dao.need, dao.description, dao.ownerId, dao.type, dao.quantity, dao.photoId, dao.acceptorId);
  }

  @PostMapping("/api/supplies/post")
  public ResponseModel postSupplies(@RequestBody PostSupplyRequestModel postRequest){

    Supply supply = new Supply(null, postRequest.name, postRequest.location, postRequest.need, postRequest.description, postRequest.ownerId, postRequest.type, postRequest.quantity, postRequest.photoId, null);
    supply = supp.postSupply(supply);
    return new SupplyResponse(supply.id, supply.name, supply.location, supply.need, supply.description, supply.ownerId, supply.type, supply.quantity, supply.photoId, supply.acceptorId);
  }

}
