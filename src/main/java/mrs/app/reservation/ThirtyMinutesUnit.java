package mrs.app.reservation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Documented
@Constraint(validatedBy = {ThirtyMinutesUnitValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ThirtyMinutesUnit {
	String message
}
