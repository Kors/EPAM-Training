package office.supplies;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Pen {

	Enum<Colors> color;
	Enum<Types> type;

	String brand;
	String model;

	double price;

	enum Colors {
		BLUE, BLACK, RED, GREEN
	}

	enum Types {
		BALL, HELIUM
	}
}
