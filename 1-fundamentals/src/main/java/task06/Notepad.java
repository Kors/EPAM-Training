package task06;

public class Notepad {

	private Note[] notes = new Note[0];

	@SuppressWarnings("WeakerAccess")
	public void addNote(String newNote) {
		Note[] temp = new Note[notes.length + 1];
		System.arraycopy(notes, 0, temp, 0, notes.length);
		notes = temp;
		notes[notes.length - 1] = new Note(newNote);
	}

	@SuppressWarnings("WeakerAccess")
	public void removeNote(int noteNumber) {
		assertLength(noteNumber);
		Note[] temp = new Note[notes.length - 1];
		System.arraycopy(notes, 0, temp, 0, noteNumber - 1);
		System.arraycopy(notes, noteNumber, temp, noteNumber - 1, notes.length - noteNumber);
		notes = temp;
	}

	private void assertLength(int noteNumber) {
		if (noteNumber > notes.length)
			throw new RuntimeException(
					String.format("Не существует заметки №%d. Всего заметок: %d", noteNumber, notes.length));
	}

	@SuppressWarnings("WeakerAccess")
	public void editNote(String note, int noteNumber) {
		assertLength(noteNumber);
		notes[noteNumber - 1].setValue(note);
	}

	@SuppressWarnings("WeakerAccess")
	public void showNotes() {
		System.out.println("Заметки в блокноте:");
		for (Note note : notes)
			System.out.printf("* %s%n", note.getValue());
	}

	public static void main(String[] args) {
		Notepad nb = new Notepad();
		nb.showNotes();
		nb.addNote("first note");
		nb.addNote("second note");
		nb.addNote("third note");
		nb.showNotes();
		nb.editNote("fourth note", 2);
		nb.editNote("fifth note", 1);
		nb.addNote("sixth note");
		nb.showNotes();
		nb.removeNote(1);
		nb.removeNote(3);
		nb.showNotes();
	}
}
