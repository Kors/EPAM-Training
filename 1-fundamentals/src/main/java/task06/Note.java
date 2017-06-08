package task06;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class is used for storing note
 *
 * @author Korsikov Ilya
 * @version 1.0
 */
@Data
@AllArgsConstructor
class Note {
	/**
	 * text value of the Note
	 */
	private String value;
}
