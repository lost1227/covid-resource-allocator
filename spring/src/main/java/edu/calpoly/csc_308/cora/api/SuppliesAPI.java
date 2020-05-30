package edu.calpoly.csc_308.cora.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;
import edu.calpoly.csc_308.cora.api.request.PostSupplyRequestModel;
import edu.calpoly.csc_308.cora.api.response.FailResponse;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse;
import edu.calpoly.csc_308.cora.api.response.SuppliesResponse.SupplyResponse;
import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.Supply;
import edu.calpoly.csc_308.cora.services.SupplyManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    if(request.getEnabledFilters().length > 0) {
      daos = suppRepo.suppliesByFilters(request);
    } else {
      daos = suppRepo.findAll();
    }


    List<SupplyResponse> supplies = daos.stream().map(
      dao -> new SupplyResponse(dao.id, dao.name, dao.location, dao.need, dao.description, dao.ownerId, dao.type, dao.quantity, dao.photoId)
    ).collect(Collectors.toList());
    
    return new SuppliesResponse(supplies);
  }

  @GetMapping("/api/supply")
  public ResponseEntity<ResponseModel> getSingleSupply(@RequestParam Long id) {
    Optional<SupplyDAO> opDao = suppRepo.findById(id);
    if(!opDao.isPresent()) {
      return ResponseEntity.badRequest().body(new FailResponse());
    }
    SupplyDAO dao = opDao.get();
    return ResponseEntity.ok().body(new SupplyResponse(dao.id, dao.name, dao.location, dao.need, dao.description, dao.ownerId, dao.type, dao.quantity, dao.photoId));
  }

  @PostMapping("/api/supplies/post")
  public ResponseModel postSupplies(@RequestBody PostSupplyRequestModel postRequest){

    Supply supply = new Supply(null, postRequest.getName(), postRequest.getLocation(), postRequest.getNeed(), postRequest.getDescription(), postRequest.getOwnerId(), postRequest.getType(), postRequest.getQuantity(), postRequest.getPhotoId());

    supply = supp.postSupply(supply);
    return SupplyResponse.fromSupply(supply);
  }

}
