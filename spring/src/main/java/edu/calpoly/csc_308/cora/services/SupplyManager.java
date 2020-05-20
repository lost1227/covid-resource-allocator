package edu.calpoly.csc_308.cora.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        SupplyDAO dao = new SupplyDAO(supply.name, supply.location, supply.need, supply.description,supply.taskOwner);
        return dao;
    }
    private Supply convertSupply(SupplyDAO dao) {
        Supply supply = new Supply(dao.name, dao.location, dao.need, dao.description,dao.taskOwner);
        return supply;
    }
    public void postSupply(Supply supply) {
        repo.save(convertSupplyDAO(supply));
    }
    
}
