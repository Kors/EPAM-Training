package university;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
class Group<T extends Number> {

	@Getter
	private SubjectMatter subject;
	private T defaultMark;

	private Map<Student, T> marks = new HashMap<>();

	Group(SubjectMatter subject, T defaultMark) {
		this.subject = subject;
		this.defaultMark = defaultMark;
	}

	void addStudents(Student... students) {
		for (Student student : students)
			marks.putIfAbsent(student, defaultMark);
	}

	void setMark(Student student, T mark) {
		marks.computeIfPresent(student, (s, m) -> mark);
	}

	void printResults() {
		StringBuilder table = new StringBuilder("Успеваемость группы студенов по дисциплине '" + subject + ":'\n" +
				"Имя студента -> Оценка\n");
		for (Map.Entry<Student, T> e : marks.entrySet())
			table.append(e.getKey().name).append(" -> ").append(e.getValue()).append("\n");
		log.info(table.toString());
	}
}
