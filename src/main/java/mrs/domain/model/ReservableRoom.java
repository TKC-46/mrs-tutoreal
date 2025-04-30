package mrs.domain.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class ReservableRoom implements Serializable {

	@EmbeddedId
	private ReservableRoomId reservableRoomId;
	
	@ManyToOne
	@JoinColumn(name = "room_id, insertable = false, updatable = false")
	@MapsId("roomId")
	private MeetingRoom meetingRoom;
}
