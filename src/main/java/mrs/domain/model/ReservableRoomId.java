package mrs.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

// 複合主キーのクラス
@Embeddable
public class ReservableRoomId implements Serializable {

	@Column(name = "room_id")
	private Integer roomId;
	
	@Column(name = "reserved_date")
	private LocalDate reservedDate;
	
	public ReservableRoomId(Integer roomId, LocalDate reservedDate) {
		this.roomId = roomId;
		this.reservedDate = reservedDate;
	}
	
	
	public ReservableRoomId() {	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reservedDate == null) ? 0 : reservedDate.hashCode());
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		return result;
	}
	
	
	/*
	 * DBから読み込んだReservableRoomIdと、画面からリクエストで受け取ったReservableRoomIdを比較して「同じもの」と判断します。
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		ReservableRoomId other = (ReservableRoomId) obj;
		
		if (reservedDate == null) {
			if (other.reservedDate != null) {
				return false;
			}
		} else if (!roomId.equals(other.roomId)) {
			return false;
		}
		
		return true;
	}


	public Integer getRoomId() {
		return roomId;
	}


	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}


	public LocalDate getReservedDate() {
		return reservedDate;
	}


	public void setReservedDate(LocalDate reservedDate) {
		this.reservedDate = reservedDate;
	}
	
}
