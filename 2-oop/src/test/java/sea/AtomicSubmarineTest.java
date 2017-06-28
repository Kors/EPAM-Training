package sea;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class AtomicSubmarineTest {
	@Test
	//@DisplayName("Лодка отправляется в плавание, её двигаитель заработал") //Junit5
	public void startEngineToMoveSubmarine() throws Exception {
		AtomicSubmarine spySubmarine = spy(new AtomicSubmarine());
		AtomicSubmarine.AtomicEngine spyEngine = spy(spySubmarine.engine);
		spySubmarine.engine = spyEngine;
		spySubmarine.move();
		verify(spySubmarine, times(1)).move();
		verify(spyEngine, times(1)).start();
	}

}