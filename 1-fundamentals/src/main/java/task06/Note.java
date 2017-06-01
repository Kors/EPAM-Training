package task06;

/**
 *  ласс служит дл€ хранени€ значени€ текстовой заметки
 *
 * @author Korsikov Ilya
 * @version 1.0
 */
class Note {
	/**
	 * переменна€ хранит текст заметки
	 */
	private String value;

	/**
	 * создаЄт новый объект с заданным значением
	 *
	 * @param value - текст заметки
	 */
	Note(String value) {
		this.value = value;
	}

	/**
	 * ¬озвращает строку с текстом заметки {@link Note#value}
	 *
	 * @return note - текст заметки
	 */
	String getValue() {
		return value;
	}

	/**
	 * »змен€ет текст заметки на заданный
	 *
	 * @param value - текст заметки
	 */
	void setValue(String value) {
		this.value = value;
	}
}
