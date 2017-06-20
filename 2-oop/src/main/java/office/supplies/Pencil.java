package office.supplies;

abstract class Pencil extends Supply {

	final Hardness hardness;

	Pencil(Hardness hardness, double price) {
		super(price);
		this.hardness = hardness;
	}

	enum Hardness {
		H, HB, B
	}
}
