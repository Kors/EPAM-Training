package serializer;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kors
 */
class FilmCollectionSerializerTest {

	@Test
	void serializing() throws IOException, ClassNotFoundException {
		Path f = Files.createTempFile("temp", "dat");
		HashSet<FilmCollectionSerializer.Tuple> before = new HashSet<>();
		before.add(FilmCollectionSerializer.Tuple.get("Форсаж", "Вин Дизель"));
		before.add(FilmCollectionSerializer.Tuple.get("Форсаж", "Пол Уокер"));
		before.add(FilmCollectionSerializer.Tuple.get("Двойной форсаж", "Пол Уокер"));
		before.add(FilmCollectionSerializer.Tuple.get("Форсаж", "Пол Уокер"));
		assertThat(before.size(), Is.is(3));

		FilmCollectionSerializer.save(before, f.toString());
		HashSet<FilmCollectionSerializer.Tuple> after = FilmCollectionSerializer.load(f.toString());

		assertThat(after, IsEqual.equalTo(before));
	}

}