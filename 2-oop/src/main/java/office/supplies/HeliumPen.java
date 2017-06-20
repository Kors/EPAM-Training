package office.supplies;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class HeliumPen extends Pen {
	public HeliumPen(Enum<Colors> color, String brand, String model, double price) {
		super(color, brand, model, price);
	}
}
