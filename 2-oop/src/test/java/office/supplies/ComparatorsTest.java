package office.supplies;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ComparatorsTest {

	private final List<Supply> supplies = new ArrayList<>();
	private final List<Supply> sortedByModel = new ArrayList<>();
	private final List<Supply> sortedByPrice = new ArrayList<>();
	private final List<Supply> sortedByModelAndPrice = new ArrayList<>();

	public ComparatorsTest() {
		Supply[] supplyObjects = {
				new BallPen(Pen.Colors.BLACK, "adidas", "1_model", 100.13),
				new HeliumPen(Pen.Colors.GREEN, "parker", "1_model", 72.5),
				new Pen(Pen.Colors.RED, "parker", "2_model", 72.4),
				new Eraser(31.5)};

		supplies.add(supplyObjects[0]);
		supplies.add(supplyObjects[1]);
		supplies.add(supplyObjects[2]);
		supplies.add(supplyObjects[3]);

		sortedByModel.add(supplyObjects[3]);
		sortedByModel.add(supplyObjects[0]);
		sortedByModel.add(supplyObjects[1]);
		sortedByModel.add(supplyObjects[2]);

		sortedByPrice.add(supplyObjects[3]);
		sortedByPrice.add(supplyObjects[2]);
		sortedByPrice.add(supplyObjects[1]);
		sortedByPrice.add(supplyObjects[0]);

		sortedByModelAndPrice.add(supplyObjects[3]);
		sortedByModelAndPrice.add(supplyObjects[1]);
		sortedByModelAndPrice.add(supplyObjects[0]);
		sortedByModelAndPrice.add(supplyObjects[2]);
	}

	@Test
	public void modelComparator() {
		supplies.sort(new SupplyModelComparator());
		assertThat("Sorting by model failed!", supplies, equalTo(sortedByModel));
		supplies.sort(new SupplyPriceComparator());
		assertThat("Sorting by price failed!", supplies, equalTo(sortedByPrice));
		supplies.sort(new SupplyModelComparator().thenComparing(new SupplyPriceComparator()));
		assertThat("Sorting by model&price failed!", supplies, equalTo(sortedByModelAndPrice));
	}

}