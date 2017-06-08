package task06;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * contains easiest tests
 * weak covering
 */
public class NotepadTest {

	private Notepad notepad = new Notepad();

	@Test
	public void initNotepad() {
		assertEquals("Not empty array after Notepad initialized.", 0, notepad.getNotesCounter());
	}

	@Test
	public void addNote() {
		notepad.addNote("1");
		assertEquals("Adding note failed. Wrong notes number inside.", 1, notepad.getNotesCounter());
	}

	@Test
	public void removeNote() {
		addNote();
		notepad.removeNote(1);
		assertEquals("Removing note failed. Wrong notes number inside.", 0, notepad.getNotesCounter());
	}

	@Test
	public void editNote() throws Exception {
		addNote();
		notepad.editNote("2", 1);
		assertEquals("Editing note failed. Wrong notes number inside.", 1, notepad.getNotesCounter());
	}

	@Test
	public void showNotes() throws Exception {
		notepad.showNotes();
		notepad.addNote("some test text 187368");
		notepad.showNotes();
	}
}
