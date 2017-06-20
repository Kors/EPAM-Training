package office;

import office.supplies.Pen;
import office.supplies.Supply;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SuppliesControlStorageTest {

	private SuppliesControlStorage scs;
	private Employee employee;

	@Before
	public void init() throws Exception {
		scs = new SuppliesControlStorage();
		employee = new Employee();
	}

	@Test
	public void addSupplyToEmployee() throws Exception {
		Pen pen = new Pen(Pen.Colors.BLUE, "PILOT", "BPGP-10R-F-L", 10.72);
		scs.addSupplyToEmployee(employee, pen);
		List<Supply> supplies = scs.getSupplies(employee);
		assertThat(supplies.size(), equalTo(1));
		assertThat(supplies.get(0), equalTo(pen));
	}

	@Test
	public void getSumPrice() throws Exception {
		double[] prises = {10.72, 24.53};
		double sumPrice = Arrays.stream(prises).sum();

		Pen pen1 = new Pen(Pen.Colors.BLUE, "PILOT", "BPGP-10R-F-L", prises[0]);
		Pen pen2 = new Pen(Pen.Colors.RED, "PILOT", "", prises[1]);
		scs.addSupplyToEmployee(employee, pen1);
		scs.addSupplyToEmployee(employee, pen2);

		assertThat(scs.getSumPrice(employee), equalTo(sumPrice));
	}

	@Test
	public void getUnavailableSumPrice() throws Exception {
		assertThat(scs.getSumPrice(employee), equalTo(0.0));
	}

}
