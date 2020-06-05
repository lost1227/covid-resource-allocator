package edu.calpoly.csc_308.cora.luis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

import java.util.List;
import java.util.stream.Collectors;

import edu.calpoly.csc_308.cora.api.request.SuppliesFilterRequestModel;
import edu.calpoly.csc_308.cora.api.response.SuccessResponse;
import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.data.supplies.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

class ChangeSupplyInfoTest {

	@Test
	void testChangeQuantity() {
		SupplyDAO.DescInfo desc = new SupplyDAO.DescInfo("Canned Food", "San Luis Obispo, CA, USA", "Food in a can.");
		SupplyDAO item = new SupplyDAO(desc, 1, 12345, SupplyType.OFFER, 47, (long)123);
		item.setQuantity(39);
		Integer quantity = item.getQuantity();
		assertThat(quantity.equals(39));
	}

	@Test
	void testChangeDescription() {
		SupplyDAO.DescInfo desc = new SupplyDAO.DescInfo("Canned Food", "San Luis Obispo, CA, USA", "Food in a can.");
		SupplyDAO item = new SupplyDAO(desc, 1, 12345, SupplyType.OFFER, 47, (long)123);
		item.setDescription("Food in a can.  Expires in 2026.");
		String description = item.getDescription();
		assertThat(description.equals("Food in a can.  Expires in 2026."));
	}
}