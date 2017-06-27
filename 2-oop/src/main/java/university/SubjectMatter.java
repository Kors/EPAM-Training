package university;

import lombok.Getter;

public enum SubjectMatter {
	MATHS("����������"),
	PHYSICS("������"),
	GEOGRAPHY("���������");

	@Getter
	private String name;

	public String toString() {
		return name;
	}

	SubjectMatter(String name) {
		this.name = name;
	}
}
