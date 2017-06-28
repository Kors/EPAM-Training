package task06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * It tests lombok
 */
class NoteTest {

	private String text = "test note";
	private Note note = new Note(text);

	@Test
	void getValue() {
		assertEquals(text, note.getValue(), "Getting value failed.");
	}

	@Test
	void setValue() {
		String newValue = "new note value";
		note.setValue(newValue);
		assertEquals(newValue, note.getValue(), "Getting value failed.");
	}
}
