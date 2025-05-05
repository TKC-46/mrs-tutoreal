package mrs.domain.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

// ある会議室がいつ予約されているか
@Entity
public class ReservableRoom implements Serializable {

	// 複合キーを管理会議室のIDと名前が含まれる
	@EmbeddedId
	private ReservableRoomId reservableRoomId;
	
	@ManyToOne
	// 二十定義を避けるtrueだとsqlで値をセットしなければならない@EmbeddedId の reservableRoomId.roomId から自動的にセット
	@JoinColumn(name = "room_id", insertable = false, updatable = false)// 関連付けるだけなので、登録や更新がされないようにする
	@MapsId("roomId")// reservableRoomId.roomId が meetingRoom.roomId とリンクされる
	private MeetingRoom meetingRoom;
	
	// reservable.setReservableRoomId(new ReservableRoomId(101, LocalDate.of(2025, 5, 1)));のように挿入される
	public ReservableRoom(ReservableRoomId reservableRoomId) {
		this.reservableRoomId = reservableRoomId;
	}
	
	public ReservableRoom() {
	}

	public ReservableRoomId getReservableRoomId() {
		return reservableRoomId;
	}

	public void setReservableRoomId(ReservableRoomId reservableRoomId) {
		this.reservableRoomId = reservableRoomId;
	}

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}
	
	
}
