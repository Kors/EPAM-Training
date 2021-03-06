package sea;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Moveable(canMoveLeft = false)
class AtomicSubmarine {

	AtomicEngine engine = new AtomicEngine();

	void move() {
		engine.start();
		log.debug("Atomic Submarine moving");
		log.debug(() -> {
			Moveable moveable = this.getClass().getAnnotation(Moveable.class);
			return "Can move right:" + moveable.canMoveRight() + "\n" +
					"Can move left:" + moveable.canMoveLeft() + "\n" +
					"Can move up:" + moveable.canMoveUp() + "\n" +
					"Can move down:" + moveable.canMoveDown() + "\n";
		});
	}

	class AtomicEngine {
		void start() {
			log.debug("Atomic Engine started");
		}
	}

	public static void main(String[] args) {
		new AtomicSubmarine().move();
	}
}
