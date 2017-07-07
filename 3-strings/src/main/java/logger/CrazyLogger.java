package logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class CrazyLogger {
	private StringBuilder data = new StringBuilder();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm");

	/**
	 * using ; are not available in this log
	 */
	synchronized void write(String s) {
		LocalDateTime dateTime = LocalDateTime.now();
		data.append(dateTime.format(formatter))
				.append(" - ")
				.append(s.replaceAll(";", ","))
				.append(";");
	}

	void findAndShow(String text) {
		String content = data.toString();
		String lowText = text.toLowerCase();
		for (String note : content.split(";"))
			if (note.toLowerCase().contains(lowText))
				System.out.println(note);
	}
}
