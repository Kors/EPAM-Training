package office.supplies;

import java.awt.*;

public class ColouredPencil extends Pencil {

	final Color color;

	public ColouredPencil(Color color, Hardness hardness, double price) {
		super(hardness, price);
		this.color = color;
	}
}
