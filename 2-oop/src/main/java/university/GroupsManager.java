package university;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupsManager {
	private Group<Integer> mathsGroup = new Group<>(SubjectMatter.MATHS, 0);
	private Group<Double> geographyGroup = new Group<>(SubjectMatter.GEOGRAPHY, 0.0);
	private Group<Byte> physicsGroup = new Group<>(SubjectMatter.PHYSICS, (byte) 0);

	public static void main(String[] args) {
		new GroupsManager().test();
	}

	private void test() {
		List<Student> students = IntStream.range(1, 7)
				.mapToObj(i -> new Student("Student ¹" + i))
				.collect(Collectors.toList());
		distributeStudents(students);
		setMarks(students);
		printResults();
	}

	private void distributeStudents(List<Student> students) {
		mathsGroup.addStudents(students.get(0), students.get(1), students.get(2));
		geographyGroup.addStudents(students.get(2), students.get(3), students.get(4));
		physicsGroup.addStudents(students.get(0), students.get(5));
	}

	private void setMarks(List<Student> students) {
		mathsGroup.setMark(students.get(0), 98);
		mathsGroup.setMark(students.get(1), 3);
		mathsGroup.setMark(students.get(2), 16);

		geographyGroup.setMark(students.get(2), 17.3);
		geographyGroup.setMark(students.get(3), 54.5);
		geographyGroup.setMark(students.get(4), 16.0);

		physicsGroup.setMark(students.get(0), (byte) 9);
		physicsGroup.setMark(students.get(5), (byte) 2);
	}

	private void printResults() {
		mathsGroup.printResults();
		geographyGroup.printResults();
		physicsGroup.printResults();
	}
}
