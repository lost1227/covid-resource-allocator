package edu.calpoly.csc_308.cora.loop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;
import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SupplyRepoLoopTest {

  @Autowired
  private SupplyRepository repo;

  ArrayList<SupplyDAO> supplies = new ArrayList<>();

  @BeforeEach
  void setup() {
    repo.deleteAll();

    
    supplies.add(new SupplyDAO(
      new SupplyDAO.DescInfo(
        "KN-95 Masks",
        "Tempe, AZ",
        "In need of extra KN-95 medical grade masks."
      ),
      1,
      5L,
      SupplyType.REQUEST,
      0,
      -1L
    ));
    supplies.add(new SupplyDAO(
      new SupplyDAO.DescInfo(
        "Face Shields",
        "Long Beach, CA",
        "In need of homemade face masks."
      ),
      0,
      6L,
      SupplyType.REQUEST,
      10,
      -1L
    ));
    supplies.add(new SupplyDAO(
      new SupplyDAO.DescInfo(
        "Canned goods",
        "Los Angeles, CA",
        "Extra canned goods available."
      ),
      0,
      6L,
      SupplyType.OFFER,
      1000,
      -1L
    ));

    repo.saveAll(supplies);
  }

  @AfterEach
  void teardown() {
    repo.deleteAll();
  }
  
  @Test
  void testSupplyRepoLoopZeroTimes() {
    SuppliesFilterRequestModel filters = new SuppliesFilterRequestModel();
    filters.setEnabledFilters(new String[]{});

    List<SupplyDAO> foundSupplies = repo.suppliesByFilters(filters);

    assertThat(foundSupplies, contains(supplies.toArray()));
  }

  @Test
  void testSupplyRepoLoopOneTime() {
    SuppliesFilterRequestModel filters = new SuppliesFilterRequestModel();
    filters.setEnabledFilters(new String[]{"search"});
    filters.setSearch("masks");

    List<SupplyDAO> foundSupplies = repo.suppliesByFilters(filters);

    assertThat(foundSupplies, contains(new SupplyDAO[]{supplies.get(0), supplies.get(1)}));
  }

  @Test
  void testSupplyRepoLoopTypicalTimes() {
    SuppliesFilterRequestModel filters = new SuppliesFilterRequestModel();
    filters.setEnabledFilters(new String[]{"search", "type"});
    filters.setSearch("masks");
    filters.setType(SupplyType.REQUEST);

    List<SupplyDAO> foundSupplies = repo.suppliesByFilters(filters);

    assertThat(foundSupplies, contains(new SupplyDAO[]{supplies.get(0), supplies.get(1)}));
  }

  @Test
  void testSupplyRepoLoopNMinus1Times() {
    SuppliesFilterRequestModel filters = new SuppliesFilterRequestModel();
    filters.setEnabledFilters(new String[]{"type", "need", "location"});
    filters.setType(SupplyType.OFFER);
    filters.setNeed(0);
    filters.setLocation("CA");

    List<SupplyDAO> foundSupplies = repo.suppliesByFilters(filters);

    assertThat(foundSupplies, contains(new SupplyDAO[]{supplies.get(2)}));
  }

  @Test
  void testSupplyRepoLoopNTimes() {
    SuppliesFilterRequestModel filters = new SuppliesFilterRequestModel();
    filters.setEnabledFilters(new String[]{"type", "need", "location", "search"});
    filters.setType(SupplyType.OFFER);
    filters.setNeed(0);
    filters.setLocation("CA");
    filters.setSearch("can");

    List<SupplyDAO> foundSupplies = repo.suppliesByFilters(filters);

    assertThat(foundSupplies, contains(new SupplyDAO[]{supplies.get(2)}));
  }

  @Test
  void testSupplyRepoLoopNPlus1Times() {
    SuppliesFilterRequestModel filters = new SuppliesFilterRequestModel();
    filters.setEnabledFilters(new String[]{"type", "need", "location", "search", "type"});
    filters.setType(SupplyType.OFFER);
    filters.setNeed(0);
    filters.setLocation("CA");
    filters.setSearch("can");

    List<SupplyDAO> foundSupplies = repo.suppliesByFilters(filters);

    assertThat(foundSupplies, contains(new SupplyDAO[]{supplies.get(2)}));
  }

  @Test
  void testSupplyRepoLoopNPlus1TimesInvalidFilter() {
    SuppliesFilterRequestModel filters = new SuppliesFilterRequestModel();
    filters.setEnabledFilters(new String[]{"type", "need", "location", "search", "some invalid filter"});
    filters.setType(SupplyType.OFFER);
    filters.setNeed(0);
    filters.setLocation("CA");
    filters.setSearch("can");

    List<SupplyDAO> foundSupplies = repo.suppliesByFilters(filters);

    assertThat(foundSupplies, contains(new SupplyDAO[]{supplies.get(2)}));
  }
  
}
