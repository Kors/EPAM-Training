package task06;

public class Notepad {

	Note[] notes;

	public void addNote(String note) {

	}

	public void removeNote(int noteNumber) {

	}

	public void editNote(String note, int noteNumber) {

	}

	public void showNotes() {

	}

	public static void main(String[] args) {
		Notepad nb = new Notepad();
		nb.addNote("first note");
		nb.addNote("second note");
		nb.addNote(" third note");
		nb.showNotes();
		nb.editNote("fourth note", 2);
		nb.removeNote(1);
		nb.showNotes();
	}
}
