package sea;

import lombok.extern.log4j.Log4j2;

@Log4j2
class AtomicSubmarine {

	private AtomicEngine engine = new AtomicEngine();

	void move() {
		engine.start();
		log.debug("Atomic Submarine moving");
	}

	class AtomicEngine {
		void start() {
			log.debug("Atomic Engine started");
		}
	}
}
