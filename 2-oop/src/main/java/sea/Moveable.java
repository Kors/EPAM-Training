package sea;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Moveable {
	boolean canMoveRight() default true;

	boolean canMoveLeft() default true;

	boolean canMoveUp() default true;

	boolean canMoveDown() default true;
}
