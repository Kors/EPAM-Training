package task06;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * It tests lombok
 */
public class NoteTest {

	private String text = "test note";
	private Note note = new Note(text);

	@Test
	public void getValue() {
		assertEquals("Getting value failed.", text, note.getValue());
	}

	@Test
	public void setValue() {
		String newValue = "new note value";
		note.setValue(newValue);
		assertEquals("Getting value failed.", newValue, note.getValue());
	}
}
