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
	private Note[] notes = new Note[10];
	/**
	 * ���������� ������ ������� ���������� ������� � �������
	 */
	private int notesCounter;

	/**
	 * ������ ����� ������� � �������� ���������
	 * � ��������� � � ���������
	 *
	 * @param noteValue - ����� �������
	 */
	@SuppressWarnings("WeakerAccess")
	public void addNote(String noteValue) {
		if (notesCounter >= notes.length)
			changeArraySize();
		notes[notesCounter] = new Note(noteValue);
		notesCounter++;
	}

	/**
	 * �������� ������ ������� (� ����������� �� �������� ���������� ������� ���������)
	 * �����������, ����� ������ ����� ��������
	 * ��� ���������, ����� ��������� ������� ������.
	 */
	private void changeArraySize() {
		Note[] temp = new Note[notesCounter + 10];
		System.arraycopy(notes, 0, temp, 0, notes.length);
		notes = temp;
	}

	/**
	 * ������� ������� �� � ������ � ���������
	 *
	 * @param noteNumber - ����� ������� � ���������
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
		System.out.println("������� � �������� (" + notesCounter + "��):");
		for (int i = 0; i < notesCounter; i++)
			System.out.printf(i + ") %s%n", notes[i].getValue());
		System.out.println();
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
