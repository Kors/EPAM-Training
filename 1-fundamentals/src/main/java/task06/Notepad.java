package task06;

/**
 * This class is used for storing array of notes and working with them
 *
 * @author Korsikov Ilya
 * @version 1.0
 */
public class Notepad {

	/**
	 * storing Notes in array
	 *
	 * @see Note
	 */
	Note[] notes = new Note[10];
	/**
	 * counting actual number of notes
	 */
	int notesCounter;

	/**
	 * create new Note with given text and add it to storage
	 *
	 * @param noteValue - text value of the note
	 * @see Note
	 */
	@SuppressWarnings("WeakerAccess")
	public void addNote(String noteValue) {
		if (notesCounter >= notes.length)
			changeArraySize();
		notes[notesCounter] = new Note(noteValue);
		notesCounter++;
	}

	/**
	 * changing size of note's array depending on actual number of notes
	 */
	private void changeArraySize() {
		Note[] temp = new Note[notesCounter + 10];
		System.arraycopy(notes, 0, temp, 0, notes.length);
		notes = temp;
	}

	/**
	 * removes a note according to its number in the storage
	 *
	 * @param noteNumber - note's number in the storage
	 */
	@SuppressWarnings("WeakerAccess")
	public void removeNote(int noteNumber) {
		assertLength(noteNumber);
		if (notesCounter < notes.length - 20)
			changeArraySize();
		System.arraycopy(notes, noteNumber, notes, noteNumber - 1, notesCounter - noteNumber);
		notes[notesCounter] = null;
		notesCounter--;
	}

	/**
	 * check if the note is in the storage
	 *
	 * @param noteNumber - note's number in the storage
	 */
	private void assertLength(int noteNumber) {
		if (noteNumber > notes.length || noteNumber < 0)
			throw new RuntimeException(
					String.format("Не существует заметки №%d. Всего заметок: %d", noteNumber, notes.length));
	}

	/**
	 * replaces contents of the note
	 *
	 * @param noteValue  - new text value of the Note
	 * @param noteNumber - note's number in the storage
	 */
	@SuppressWarnings("WeakerAccess")
	public void editNote(String noteValue, int noteNumber) {
		assertLength(noteNumber);
		notes[noteNumber - 1].setValue(noteValue);
	}

	/**
	 * show all notes
	 */
	@SuppressWarnings("WeakerAccess")
	public void showNotes() {
		System.out.println("Заметки в блокноте (" + notesCounter + "шт):");
		for (int i = 0; i < notesCounter; i++)
			System.out.printf(i + ") %s%n", notes[i].getValue());
		System.out.println();
	}

	/**
	 * just for test
	 */
	public static void main(String[] args) {
		Notepad np = new Notepad();
		np.showNotes();
		np.addNote("first note");
		np.addNote("second note");
		np.addNote("third note");
		np.showNotes();
		np.editNote("fourth note", 2);
		np.editNote("fifth note", 1);
		np.addNote("sixth note");
		np.showNotes();
		np.removeNote(1);
		np.removeNote(3);
		np.showNotes();
	}
}
