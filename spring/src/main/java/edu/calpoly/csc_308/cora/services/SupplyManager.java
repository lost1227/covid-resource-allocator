package edu.calpoly.csc_308.cora.services;


import org.springframework.stereotype.Service;

import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.Supply;
import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;


@Service
public class SupplyManager {

    private SupplyRepository repo;

    public SupplyManager(SupplyRepository repo) {
        this.repo = repo;
    }
    private SupplyDAO convertSupplyDAO(Supply supply) {
        SupplyDAO dao = new SupplyDAO(supply.getName(), supply.getLocation(), supply.getNeed(), supply.getDescription(), supply.getOwnerId(), supply.getType(), supply.getQuantity(), supply.getPhotoId());
        return dao;
    }
    private Supply convertSupply(SupplyDAO dao) {
        Supply supply = new Supply(dao.id, dao.name, dao.location, dao.need, dao.description, dao.ownerId, dao.type, dao.quantity, dao.photoId);
        return supply;
    }
    public Supply postSupply(Supply supply) {
      SupplyDAO dao = convertSupplyDAO(supply);  
      dao = repo.save(convertSupplyDAO(supply));
      return convertSupply(dao);
    }
    
}
