package edu.calpoly.csc_308.cora.luis;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

class ChangeSupplyInfoTest {

	@Test
	void testChangeQuantity() {
		SupplyDAO.DescInfo desc = new SupplyDAO.DescInfo("Canned Food", "San Luis Obispo, CA, USA", "Food in a can.");
		SupplyDAO item = new SupplyDAO(desc, 1, 12345, SupplyType.OFFER, 47, (long)123);
		item.setQuantity(39);
		Integer quantity = item.getQuantity();
		assertThat(quantity, equalTo(39));
	}

	@Test
	void testChangeDescription() {
		SupplyDAO.DescInfo desc = new SupplyDAO.DescInfo("Canned Food", "San Luis Obispo, CA, USA", "Food in a can.");
		SupplyDAO item = new SupplyDAO(desc, 1, 12345, SupplyType.OFFER, 47, (long)123);
		item.setDescription("Food in a can.  Expires in 2026.");
		String description = item.getDescription();
		assertThat(description, equalTo("Food in a can.  Expires in 2026."));
	}
}
