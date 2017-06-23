package office.supplies;

import java.util.Comparator;

public class SupplyPriceComparator implements Comparator<Supply> {
	@Override
	public int compare(Supply a, Supply b) {
		return Double.compare(a.price, b.price);
	}
}
