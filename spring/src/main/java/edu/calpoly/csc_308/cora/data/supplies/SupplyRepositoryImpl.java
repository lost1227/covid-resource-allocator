package edu.calpoly.csc_308.cora.data.supplies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;

public class SupplyRepositoryImpl implements SupplyRepositoryCustom {

  Logger logger = LoggerFactory.getLogger(SupplyRepositoryCustom.class);
  
  @PersistenceContext
  private EntityManager em;
  
  @Override
  public List<SupplyDAO> suppliesByFilters(SuppliesFilterRequestModel filters) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<SupplyDAO> query = builder.createQuery(SupplyDAO.class);

    Root<SupplyDAO> supply = query.from(SupplyDAO.class);

    ArrayList<Predicate> predicates = new ArrayList<>();

    for(String filter : filters.getEnabledFilters()) {
      switch(filter) {
        case "type":
          predicates.add(builder.equal(supply.get("type"), filters.getType()));
          break;
        case "need":
          predicates.add(builder.equal(supply.get("need"), filters.getNeed()));
          break;
        case "location":
          predicates.add(builder.like(builder.lower(supply.get("location")), "%" + filters.getLocation().toLowerCase() + "%"));
          break;
        case "search":
          String searchStr = "%" + filters.getSearch().toLowerCase() + "%";
          predicates.add(
            builder.or(
              builder.like(builder.lower(supply.get("name")), searchStr),
              builder.like(builder.lower(supply.get("description")), searchStr)));
          break;
      }
    }

    query.where(predicates.toArray(new Predicate[]{}));

    TypedQuery<SupplyDAO> typedQuery = em.createQuery(query);
    return typedQuery.getResultList();
  }

}
