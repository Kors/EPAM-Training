package office.supplies;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BeginnerSet {
	public static List<Supply> getBeginnerSupplySet() {
		List<Supply> beginnerSet = new ArrayList<>();
		beginnerSet.add(new BallPen(Pen.Colors.BLUE, "PILOT", "BPGP-10R-F-L", 10.72));
		beginnerSet.add(new Pencil(Pencil.Hardness.HB, 12.20));
		beginnerSet.add(new ColouredPencil(Color.RED, Pencil.Hardness.H, 17.03));
		beginnerSet.add(new ColouredPencil(Color.GREEN, Pencil.Hardness.B, 14.89));
		return beginnerSet;
	}
}