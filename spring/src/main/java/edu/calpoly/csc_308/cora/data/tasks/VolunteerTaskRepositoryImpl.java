package edu.calpoly.csc_308.cora.data.tasks;

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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;

public class VolunteerTaskRepositoryImpl implements VolunteerTaskRepositoryCustom {

  Logger logger = LoggerFactory.getLogger(VolunteerTaskRepositoryCustom.class);
  
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<VolunteerTaskDAO> tasksByFilters(VolunteerFilterRequestModel filters) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<VolunteerTaskDAO> query = builder.createQuery(VolunteerTaskDAO.class);

    Root<VolunteerTaskDAO> task = query.from(VolunteerTaskDAO.class);

    ArrayList<Predicate> predicates = new ArrayList<>();

    for(String filter : filters.getEnabledFilters()) {
      switch(filter) {
        case "skillSet":
          predicates.add(
            builder.or(
              Arrays.stream(filters.getSkillSet())
              .map(skill -> builder.equal(builder.lower(task.get("skillNeeded")), skill.toLowerCase()))
              .collect(Collectors.toList())
              .toArray(new Predicate[]{})));
          break;
        case "need":
          predicates.add(builder.equal(task.get("need"), filters.getNeed()));
          break;
        case "location":
          predicates.add(builder.like(builder.lower(task.get("location")), "%" + filters.getLocation().toLowerCase() + "%"));
          break;
        case "search":
          String searchStr = "%" + filters.getSearch().toLowerCase() + "%";
          predicates.add(
            builder.or(
              builder.like(builder.lower(task.get("name")), searchStr),
              builder.like(builder.lower(task.get("description")), searchStr)));
          break;
      }
    }
    query.where(predicates.toArray(new Predicate[]{}));

    TypedQuery<VolunteerTaskDAO> typedQuery = em.createQuery(query);
    return typedQuery.getResultList();
  }

}
