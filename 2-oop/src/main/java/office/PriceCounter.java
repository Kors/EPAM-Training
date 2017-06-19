package office;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import office.supplies.Pen;
import office.supplies.Supply;

import java.util.*;

@Log4j2
public class PriceCounter {
	@Getter
	private Map<Employee, List<Supply>> empSupplies = new HashMap();

	private void addSupplyToEmployee(Employee employee, Supply supply) {
		List<Supply> supplies = empSupplies.getOrDefault(employee, new ArrayList<>());
		supplies.add(supply);
		empSupplies.put(employee, supplies);
	}

	public double getSumPrice(Employee e) {
		return empSupplies
				.getOrDefault(e, Collections.emptyList())
				.parallelStream()
				.mapToDouble(Supply::getPrice)
				.sum();
	}

	public static void main(String[] args) {
		Employee employee = new Employee();
		Pen pen1 = new Pen(Pen.Colors.BLUE, Pen.Types.BALL, "PILOT", "BPGP-10R-F-L", 10.72);
		Pen pen2 = new Pen(Pen.Colors.RED, Pen.Types.HELIUM, "PILOT", "", 24.53);

		PriceCounter pc = new PriceCounter();
		pc.addSupplyToEmployee(employee, pen1);
		pc.addSupplyToEmployee(employee, pen2);
		log.info(() -> "The employee has office supplies the sum of " + pc.getSumPrice(employee) + " rub.");
	}
}
