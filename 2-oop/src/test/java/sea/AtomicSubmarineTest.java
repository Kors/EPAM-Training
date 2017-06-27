package sea;

import org.junit.Test;

public class AtomicSubmarineTest {
	@Test
	//@DisplayName("Лодка отправляется в плавание") //Junit5
	public void moveSubmarine() throws Exception {
		new AtomicSubmarine().move();
	}

}