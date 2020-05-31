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

    public Supply postSupply(Supply supply) {
      SupplyDAO dao = convertSupplyDAO(supply);  
      dao = repo.save(dao);
      return Supply.fromDAO(dao);
    }
    
}
