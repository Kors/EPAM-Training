package task06;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * tests
 */
class NotepadTest {

	private Notepad notepad = new Notepad();

	@Test
	void initNotepad() {
		assertEquals(0, notepad.notesCounter, "Not empty array after Notepad initialized.");
	}

	@Test
	void addNote() {
		String noteValue = "note ¹1";
		notepad.addNote(noteValue);
		assertThat("Adding note failed. Wrong notes number inside.", notepad.notesCounter, is(1));
		assertThat("Adding note failed. Wrong note value inside.", notepad.notes[0].getValue(), is(noteValue));
	}

	@Test
	void removeNote() {
		notepad.addNote("note ¹1");
		notepad.removeNote(1);
		assertThat("Removing note failed. Wrong notes number inside.", notepad.notesCounter, is(0));
	}

	@Test
	void editNote() throws Exception {
		notepad.addNote("note ¹1");
		String newNoteValue = "this is note ¹2";
		notepad.editNote(newNoteValue, 1);
		assertThat("Editing note failed. Wrong notes number inside.", notepad.notesCounter, is(1));
		assertThat("Editing note failed. Wrong note value inside.", notepad.notes[0].getValue(), is(newNoteValue));
	}

	@Test
	void showNotes() throws Exception {
		PrintStream out = System.out;
		ByteOutputStream testStream = new ByteOutputStream();
		PrintStream ps = new PrintStream(testStream);
		System.setOut(ps);
		String text = "some test text 187368";
		notepad.addNote(text);
		notepad.showNotes();
		System.setOut(out);
		assertThat(new String(testStream.getBytes()), containsString(text));
	}
}
