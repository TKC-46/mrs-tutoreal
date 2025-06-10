package mrs.app.reservation;

import java.time.LocalTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ThirtyMinutesUnitValidator implements ConstraintValidator<ThirtyMinutesUnit, LocalTime>{

	@Override
	public void initialize(ThirtyMinutesUnit constraintAnnotation) 
	{
		
	}
	
	
	// 入力値がnullの場合はここではチェックしない
	// 30分で割り切れるかチェック
	@Override
	public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return value.getMinute() % 30 == 0;
	}
}
