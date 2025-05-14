package mrs.domain.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationId;
	
	// DBにある開始時間
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "reserved_date"), 
			@JoinColumn(name = "room_id") })// reserved_date と room_idの組み合わせで外部キーとする
	private ReservableRoom reservableRoom;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
	/* 
	 * 重複チェック
	 * @target 今回登録したい予約
	 */
	public boolean overlap(Reservation target) {
		// 複合主キーのオブジェクトが既にDBに登録されている日付・部屋と違えば重複していない＝false
		if (!Objects.equals(reservableRoom.getReservableRoomId(), target.reservableRoom.getReservableRoomId())) {
			return false;
		}
		// 開始時間と終了時間が同じなら重複
		if (startTime.equals(target.startTime) && endTime.equals(target.startTime)) {
			return true;
		}
		
		// 入力された予約終了時間が既存予約開始時間より後 or 既存予約終了時間が入力された予約開始時間より後
		// ⇒途中でかぶっている可能性あり
		// true:重複　false:重複なし
		return target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime);
	}
	

	// Getter,Setter
	public Integer getReservationId() {
		return reservationId;
	}


	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}


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


	public ReservableRoom getReservableRoom() {
		return reservableRoom;
	}


	public void setReservableRoom(ReservableRoom reservableRoom) {
		this.reservableRoom = reservableRoom;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
