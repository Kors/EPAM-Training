package office.supplies;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
public class Pen extends Supply{

	Enum<Colors> color;
	Enum<Types> type;

	String brand;
	String model;

	@Getter
	double price;

	public enum Colors {
		BLUE, BLACK, RED, GREEN
	}

	public enum Types {
		BALL, HELIUM
	}
}
