package mrs.app.reservation;

import java.io.Serializable;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@EndTimeMustBeAfterStartTime(message = "終了時刻は開始時刻より後にしてください")
public class ReservationForm implements Serializable {
	
	@NotNull(message = "必須です")
	@ThirtyMinutesUnit(message = "30分単位で入力してください")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;
	
	@NotNull(message = "必須です")
	@ThirtyMinutesUnit(message = "30分単位で入力してください")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}
