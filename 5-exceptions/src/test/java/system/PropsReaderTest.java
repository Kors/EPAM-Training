package system;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

class PropsReaderTest {

	private PropsReader reader = new PropsReader();
	private Map<String, String> data;

	private final String propsPath = PropsReaderTest.class.getResource("/example.properties").getPath();

	@BeforeEach
	void setUp() {
		data = new HashMap<>();
		data.put("locale", "ru");
		data.put("db.host", "127.0.0.1");
		data.put("db.login", "root");
		data.put("db.password", "1234");
	}

	@Test
	void loadProps() {
		reader.loadProps("/noSuchFile.properties");
		assertThat(reader.props.entrySet(), IsEqual.equalTo(Collections.emptyMap().entrySet()));

		reader.loadProps(propsPath);
		assertThat(reader.props.entrySet(), IsEqual.equalTo(data.entrySet()));
	}

	@Test
	void readProperty() {
		assertThat(reader.readProperty("locale"), Is.is((String) null));

		reader.loadProps(propsPath);
		assertThat(reader.readProperty("locale"), Is.is("ru"));
	}

}