package task06;

/**
 * ����� ������ ��� ������ � ���������� ��������� � �� ��������
 *
 * @author Korsikov Ilya
 * @version 1.0
 */
public class Notepad {

	/**
	 * ���������� ������ ������ ��������� �������
	 */
	private Note[] notes = new Note[0];

	/**
	 * ������ ����� ������� � �������� ���������
	 * � ��������� � � ���������
	 *
	 * @param noteValue - ����� �������
	 */
	@SuppressWarnings("WeakerAccess")
	public void addNote(String noteValue) {
		Note[] temp = new Note[notes.length + 1];
		System.arraycopy(notes, 0, temp, 0, notes.length);
		notes = temp;
		notes[notes.length - 1] = new Note(noteValue);
	}


	/**
	 * ������� ������� �� � ������ � ���������
	 *
	 * @param noteNumber - ����� ������� � ���������
	 */
	@SuppressWarnings("WeakerAccess")
	public void removeNote(int noteNumber) {
		assertLength(noteNumber);
		Note[] temp = new Note[notes.length - 1];
		System.arraycopy(notes, 0, temp, 0, noteNumber - 1);
		System.arraycopy(notes, noteNumber, temp, noteNumber - 1, notes.length - noteNumber);
		notes = temp;
	}

	/**
	 * ��������� ������� ������� � ��������� �������
	 *
	 * @param noteNumber - ����� ������� � ���������
	 */
	private void assertLength(int noteNumber) {
		if (noteNumber > notes.length || noteNumber < 0)
			throw new RuntimeException(
					String.format("�� ���������� ������� �%d. ����� �������: %d", noteNumber, notes.length));
	}

	/**
	 * �������� ���������� ������� � ��������� �������
	 *
	 * @param noteValue  - ����� �������
	 * @param noteNumber - ����� ������� � ���������
	 */
	@SuppressWarnings("WeakerAccess")
	public void editNote(String noteValue, int noteNumber) {
		assertLength(noteNumber);
		notes[noteNumber - 1].setValue(noteValue);
	}

	/**
	 * ���������� ��� �������
	 */
	@SuppressWarnings("WeakerAccess")
	public void showNotes() {
		System.out.println("������� � ��������:");
		for (Note note : notes)
			System.out.printf("* %s%n", note.getValue());
	}

	/**
	 * �������� ������
	 */
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
