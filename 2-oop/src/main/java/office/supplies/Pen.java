package office.supplies;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Pen extends Supply {

	Enum<Colors> color;

	public Pen(Enum<Colors> color, String brand, String model, double price) {
		super(price, brand, model);
		this.color = color;
	}

	public enum Colors {
		BLUE, BLACK, RED, GREEN
	}
}
