package serializer;

import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;

public class FilmCollectionSerializer {

	public static void main(String[] args) {
		HashSet<Tuple> filmCollection = load();
		filmCollection.add(Tuple.get("В погоне за http-сервером", "Корсиков Илья"));
		System.out.println(filmCollection);
		save(filmCollection);
	}

	private static HashSet<Tuple> load() {
		return new HashSet<>();
	}

	private static void save(HashSet<Tuple> filmCollection) {
	}

	@ToString
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
