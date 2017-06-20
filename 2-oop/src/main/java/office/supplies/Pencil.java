package office.supplies;

public class Pencil extends Supply {

	final Hardness hardness;

	public Pencil(Hardness hardness, double price) {
		super(price);
		this.hardness = hardness;
	}

	public enum Hardness {
		H, HB, B
	}
}
