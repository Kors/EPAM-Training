package task06;

/**
 * Класс служит для работы с текстовыми заметками и их хранения
 *
 * @author Korsikov Ilya
 * @version 1.0
 */
public class Notepad {

	/**
	 * переменная хранит массив созданных заметок
	 */
	private Note[] notes = new Note[10];
	/**
	 * переменная хранит текущее количество заметок в массиве
	 */
	private int notesCounter;

	/**
	 * создаёт новую заметку с заданным значением
	 * и добавляет её в хранилище
	 *
	 * @param noteValue - текст заметки
	 */
	@SuppressWarnings("WeakerAccess")
	public void addNote(String noteValue) {
		if (notesCounter >= notes.length)
			changeArraySize();
		notes[notesCounter] = new Note(noteValue);
		notesCounter++;
	}

	/**
	 * изменяет размер массива (в зависимости от текушего количества занятых элементов)
	 * увеличивает, чтобы влезли новые элементы
	 * или уменьшает, чтобы уменьшить занятую память.
	 */
	private void changeArraySize() {
		Note[] temp = new Note[notesCounter + 10];
		System.arraycopy(notes, 0, temp, 0, notes.length);
		notes = temp;
	}

	/**
	 * удаляет заметку по её номеру в хранилище
	 *
	 * @param noteNumber - номер заметки в хранилище
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
	 * проверяет наличие заметки с указанным номером
	 *
	 * @param noteNumber - номер заметки в хранилище
	 */
	private void assertLength(int noteNumber) {
		if (noteNumber > notes.length || noteNumber < 0)
			throw new RuntimeException(
					String.format("Не существует заметки №%d. Всего заметок: %d", noteNumber, notes.length));
	}

	/**
	 * заменяет содержимое заметки с указанным номером
	 *
	 * @param noteValue  - текст заметки
	 * @param noteNumber - номер заметки в хранилище
	 */
	@SuppressWarnings("WeakerAccess")
	public void editNote(String noteValue, int noteNumber) {
		assertLength(noteNumber);
		notes[noteNumber - 1].setValue(noteValue);
	}

	/**
	 * отображает все заметки
	 */
	@SuppressWarnings("WeakerAccess")
	public void showNotes() {
		System.out.println("Заметки в блокноте (" + notesCounter + "шт):");
		for (int i = 0; i < notesCounter; i++)
			System.out.printf(i + ") %s%n", notes[i].getValue());
		System.out.println();
	}

	/**
	 * тестовый запуск
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
