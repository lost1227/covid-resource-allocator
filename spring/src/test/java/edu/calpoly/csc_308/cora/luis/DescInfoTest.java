package edu.calpoly.csc_308.cora.luis;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import edu.calpoly.csc_308.cora.data.supplies.SupplyDAO;
import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

class DescInfoTest {

	@Test
	void testDescInfoName() {
		SupplyDAO.DescInfo desc = new SupplyDAO.DescInfo("Canned Food", "San Luis Obispo, CA, USA", "Food in a can.");
		SupplyDAO item = new SupplyDAO(desc, 1, 12345, SupplyType.OFFER, 47, (long)123);
		String name = item.getName();
		assertThat(name.equals("Canned Food"));
	}

	@Test
	void testDescInfoDescription() {
		SupplyDAO.DescInfo desc = new SupplyDAO.DescInfo("Canned Food", "San Luis Obispo, CA, USA", "Food in a can.");
		SupplyDAO item = new SupplyDAO(desc, 1, 12345, SupplyType.OFFER, 47, (long)123);
		String description = item.getDescription();
		assertThat(description.equals("Food in a can."));
	}
}
