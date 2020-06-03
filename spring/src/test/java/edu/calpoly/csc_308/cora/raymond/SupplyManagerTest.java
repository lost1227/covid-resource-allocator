package edu.calpoly.csc_308.cora.raymond;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

import java.util.List;
import java.util.stream.Collectors;

import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.Supply;
import edu.calpoly.csc_308.cora.services.SupplyManager;

@AutoConfigureTestDatabase
@SpringBootTest
public class SupplyManagerTest {

    @Autowired
    private SupplyManager supp;

    @Autowired
    private SupplyRepository repo;

  @BeforeEach
  void setup() {
    repo.deleteAll();
  }

  @Test
  void testPostSupply() {
    assertThat(repo.findAll(), is(empty()));

    Supply supply = new Supply();
    supply = supp.postSupply(supply);
    
    List<Supply> storedSupplies = repo.findAll().stream().map(Supply::fromDAO).collect(Collectors.toList());

    assertThat(storedSupplies, contains(new Supply[]{supply}));
  }
  @Test
  void testConvertSupply() {
    Supply supply = new Supply();
    supply.setName("Test");
    supply.setLocation("Test");
    supply.setNeed(0);
    supply.setDescritpion("Test");
    supply.setOwnerId(0);
    SupplyDAO suppDAO = supp.convertSupplyDAO(supply);
    assertThat(suppDAO.getName().equals(supply.getName()));
    assertThat(suppDAO.getLocation().equals(supply.getLocation()));
    assertThat(suppDAO.getNeed().equals(supply.getNeed()));
    assertThat(suppDAO.getDescription().equals(supply.getDescription()));
    assertThat(suppDAO.getOwnerId().equals(supply.getOwnerId()));
    assertThat(suppDAO.getType().equals(supply.getType()));
    assertThat(suppDAO.getQuantity().equals(supply.getQuantity()));
    assertThat(suppDAO.getPhotoId().equals(supply.getPhotoId()));
  }

}