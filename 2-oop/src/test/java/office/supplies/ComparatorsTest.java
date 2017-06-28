package office.supplies;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ComparatorsTest {

	private final Supply[] supplyObjects = {
			new BallPen(Pen.Colors.BLACK, "adidas", "1_model", 100.13),
			new HeliumPen(Pen.Colors.GREEN, "parker", "1_model", 72.5),
			new Pen(Pen.Colors.RED, "parker", "2_model", 72.4),
			new Eraser(31.5)};

	private List<Supply> supplies;
	private final List<Supply> sortedByModel;
	private final List<Supply> sortedByPrice;
	private final List<Supply> sortedByModelAndPrice;

	ComparatorsTest() {
		List<Supply> l1 = new ArrayList<>();
		l1.add(supplyObjects[3]);
		l1.add(supplyObjects[0]);
		l1.add(supplyObjects[1]);
		l1.add(supplyObjects[2]);
		sortedByModel = java.util.Collections.unmodifiableList(l1);

		List<Supply> l2 = new ArrayList<>();
		l2.add(supplyObjects[3]);
		l2.add(supplyObjects[2]);
		l2.add(supplyObjects[1]);
		l2.add(supplyObjects[0]);
		sortedByPrice = java.util.Collections.unmodifiableList(l2);

		List<Supply> l3 = new ArrayList<>();
		l3.add(supplyObjects[3]);
		l3.add(supplyObjects[1]);
		l3.add(supplyObjects[0]);
		l3.add(supplyObjects[2]);
		sortedByModelAndPrice = java.util.Collections.unmodifiableList(l3);
	}

	@BeforeEach
	void init() {
		supplies = new ArrayList<>();
		supplies.add(supplyObjects[0]);
		supplies.add(supplyObjects[1]);
		supplies.add(supplyObjects[2]);
		supplies.add(supplyObjects[3]);
	}

	@Test
	void supplyModelComparator() {
		supplies.sort(new SupplyModelComparator());
		assertThat("Sorting by model failed!", supplies, equalTo(sortedByModel));
	}

	@Test
	void supplyPriceComparator() {
		supplies.sort(new SupplyPriceComparator());
		assertThat("Sorting by price failed!", supplies, equalTo(sortedByPrice));
	}

	@Test
	void supplyModelAndPriceComparator() {
		supplies.sort(new SupplyModelComparator().thenComparing(new SupplyPriceComparator()));
		assertThat("Sorting by model&price failed!", supplies, equalTo(sortedByModelAndPrice));
	}

}