package office.supplies;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;

class BeginnerSetTest {

	@Test
	void getBeginnerSupplySet() throws Exception {
		List<Supply> oneSet = BeginnerSet.getBeginnerSupplySet();
		List<Supply> anotherSet = BeginnerSet.getBeginnerSupplySet();
		assertThat(oneSet, notNullValue());
		assertThat("Empty set!", oneSet.size() > 0);
		assertThat(oneSet, everyItem(instanceOf(Supply.class)));
		assertThat(oneSet, equalTo(anotherSet));
	}

}
