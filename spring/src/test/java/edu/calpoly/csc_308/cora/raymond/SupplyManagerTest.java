package edu.calpoly.csc_308.cora.raymond;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

import java.util.List;
import java.util.stream.Collectors;

import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.Supply;
import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;
import edu.calpoly.csc_308.cora.services.SupplyManager;

@AutoConfigureTestDatabase
@SpringBootTest
class SupplyManagerTest {

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
   
    Supply s = new Supply(null, "name", "Location", 0, "desc", 1L, SupplyType.REQUEST, 0, 2L);
    repo.save(supp.convertSupplyDAO(s));
    List<Supply> storedSupplies = repo.findAll().stream().map(Supply::fromDAO).collect(Collectors.toList());

    assertThat(storedSupplies, contains(new Supply[]{s}));
  }

  @Test
  void testSupply() {

  Supply s = new Supply(null, "name", "Location", 0, "desc", 1L, SupplyType.REQUEST, 0, 2L);
  SupplyDAO sd = supp.convertSupplyDAO(s);

  assertThat(s.getName().equals(sd.getName())).isTrue();
  assertThat(s.getLocation().equals(sd.getLocation())).isTrue();
  assertThat(s.getNeed().equals(sd.getNeed())).isTrue();
  assertThat(s.getDescription().equals(sd.getDescription())).isTrue();
  assertThat(s.getOwnerId().equals(sd.getOwnerId())).isTrue();
  assertThat(s.getType().equals(sd.getType())).isTrue();
  assertThat(s.getQuantity().equals(sd.getQuantity())).isTrue();
  assertThat(s.getPhotoId().equals(sd.getPhotoId())).isTrue();

  }

}