package office.supplies;

import java.util.ArrayList;
import java.util.List;

public class BeginnerSet {
	public static List<Supply> getBeginnerSupplySet() {
		List<Supply> beginnerSet = new ArrayList<>();
		beginnerSet.add(new Pen(Pen.Colors.BLUE, Pen.Types.BALL, "PILOT", "BPGP-10R-F-L", 10.72));
		return beginnerSet;
	}
}
