package office;

import office.supplies.Supply;

import java.util.*;

class SuppliesControlStorage {

	private Map<Employee, List<Supply>> empSupplies = new HashMap<>();

	List<Supply> getSupplies(Employee employee) {
		return empSupplies.get(employee);
	}

	void addSupplyToEmployee(Employee employee, Supply supply) {
		List<Supply> supplies = empSupplies.getOrDefault(employee, new ArrayList<>());
		supplies.add(supply);
		empSupplies.put(employee, supplies);
	}

	double getSumPrice(Employee e) {
		return empSupplies
				.getOrDefault(e, Collections.emptyList())
				.parallelStream()
				.mapToDouble(Supply::getPrice)
				.sum();
	}

}
