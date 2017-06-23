package office.supplies;

import java.util.Comparator;

public class SupplyModelComparator implements Comparator<Supply> {
	@Override
	public int compare(Supply a, Supply b) {
		return a.model.compareTo(b.model);
	}
}
