package serializer;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class FilmCollectionSerializer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		HashSet<Tuple> filmCollection = load();
		filmCollection.add(Tuple.get("В погоне за http-сервером", "Корсиков Илья"));
		String film;
		String actor;
		try (Scanner scanner = new Scanner(System.in)) { // при закрытии закроет входной поток программе.
			System.out.println("Введите фильм:");
			film = scanner.nextLine();
			System.out.println("Введите актёра в главной роли:");
			actor = scanner.nextLine();
		}
		filmCollection.add(Tuple.get(film, actor));
		System.out.println(filmCollection);
		save(filmCollection);
	}

	private static HashSet<Tuple> load() throws IOException, ClassNotFoundException {
		File file = new File("filmCollection");
		if (!file.exists())
			return new HashSet<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			//noinspection unchecked
			return (HashSet<Tuple>) ois.readObject();
		}
	}

	private static void save(HashSet<Tuple> filmCollection) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("filmCollection"))) {
			oos.writeObject(filmCollection);
		}
	}

	@ToString
	@EqualsAndHashCode
	static class Tuple implements java.io.Serializable {
		private final String film;
		private final String actor;

		private Tuple(String film, String actor) {
			this.film = film;
			this.actor = actor;
		}

		static Tuple get(String film, String actor) {
			return new Tuple(film, actor);
		}
	}
}
