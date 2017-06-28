package office;

import office.supplies.Pen;
import office.supplies.Pencil;
import office.supplies.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class SuppliesControlStorageTest {

	private SuppliesControlStorage scs;
	private Employee employee;

	@BeforeEach
	void init() throws Exception {
		scs = new SuppliesControlStorage();
		employee = new Employee();
	}

	@Test
	void addSupplyToEmployee() throws Exception {
		Pen pen = new Pen(Pen.Colors.BLUE, "PILOT", "BPGP-10R-F-L", 10.72);
		scs.addSupplyToEmployee(employee, pen);
		List<Supply> supplies = scs.getSupplies(employee);
		assertThat(supplies.size(), equalTo(1));
		assertThat(supplies.get(0), equalTo(pen));
	}

	@Test
	void getSumPrice() throws Exception {
		double[] prises = {10.72, 24.53};
		double sumPrice = Arrays.stream(prises).sum();

		Pen pen = new Pen(Pen.Colors.BLUE, "PILOT", "BPGP-10R-F-L", prises[0]);
		Pencil pencil = new Pencil(Pencil.Hardness.H, prises[1]);
		scs.addSupplyToEmployee(employee, pen);
		scs.addSupplyToEmployee(employee, pencil);

		assertThat(scs.getSumPrice(employee), equalTo(sumPrice));
	}

	@Test
	void getUnavailableSumPrice() throws Exception {
		assertThat(scs.getSumPrice(employee), equalTo(0.0));
	}

}
