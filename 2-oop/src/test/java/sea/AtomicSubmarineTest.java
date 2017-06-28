package sea;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AtomicSubmarineTest {
	@Test
	@DisplayName("Лодка отправляется в плавание, её двигаитель заработал")
	void startEngineToMoveSubmarine() throws Exception {
		AtomicSubmarine submarine = new AtomicSubmarine();
		submarine.engine = spy(submarine.engine);
		submarine.move();
		verify(submarine.engine, times(1)).start();
	}

}