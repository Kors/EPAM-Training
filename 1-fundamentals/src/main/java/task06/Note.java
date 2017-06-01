package task06;

/**
 * ����� ������ ��� �������� �������� ��������� �������
 *
 * @author Korsikov Ilya
 * @version 1.0
 */
class Note {
	/**
	 * ���������� ������ ����� �������
	 */
	private String value;

	/**
	 * ������ ����� ������ � �������� ���������
	 *
	 * @param value - ����� �������
	 */
	Note(String value) {
		this.value = value;
	}

	/**
	 * ���������� ������ � ������� ������� {@link Note#value}
	 *
	 * @return note - ����� �������
	 */
	String getValue() {
		return value;
	}

	/**
	 * �������� ����� ������� �� ��������
	 *
	 * @param value - ����� �������
	 */
	void setValue(String value) {
		this.value = value;
	}
}
