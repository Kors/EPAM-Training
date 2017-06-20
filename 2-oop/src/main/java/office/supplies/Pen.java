package office.supplies;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Pen extends Supply {

	Enum<Colors> color;

	String brand;
	String model;

	public Pen(Enum<Colors> color, String brand, String model, double price) {
		super(price);
		this.color = color;
		this.brand = brand;
		this.model = model;
	}

	public enum Colors {
		BLUE, BLACK, RED, GREEN
	}
}
