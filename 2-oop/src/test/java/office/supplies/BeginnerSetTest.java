package office.supplies;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class BeginnerSetTest {

	@Test
	public void getBeginnerSupplySet() throws Exception {
		List<Supply> oneSet = BeginnerSet.getBeginnerSupplySet();
		List<Supply> anotherSet = BeginnerSet.getBeginnerSupplySet();
		assertThat(oneSet, notNullValue());
		assertTrue("Empty set!", oneSet.size() > 0);
		assertThat(oneSet, everyItem(instanceOf(Supply.class)));
		assertThat(oneSet, equalTo(anotherSet));
	}

}
