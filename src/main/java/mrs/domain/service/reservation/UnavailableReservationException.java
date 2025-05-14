package mrs.domain.service.reservation;

public class UnavailableReservationException extends RuntimeException {
	// 予約対象の部屋・日付が存在しないなどの、予約不可能な状況に投げる
	public UnavailableReservationException(String message) {
		super(message);
	}
}
