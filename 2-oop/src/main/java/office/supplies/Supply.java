package office.supplies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public abstract class Supply {
	double price;

	String brand;
	String model; // будем считать что это поле обозначает "Наименование" (task 4)

	Supply(double price) {
		this(price, "", "");
	}
}
