package mrs.domain.service.room;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.repository.room.MeetingRoomRepository;
import mrs.domain.repository.room.ReservableRoomRepository;

@Service
@Transactional// 処理途中でエラーが起きたら変更は無効化される
public class RoomService {

	@Autowired
	ReservableRoomRepository reservableRoomRepository;
	
	@Autowired
	MeetingRoomRepository meetingRoomRepository;
	
	// 会議室情報の取得
	public MeetingRoom findMeetingRoom(Integer roomId) {
		MeetingRoom meetingRoom = meetingRoomRepository.findById(roomId).orElse(null);
		
		// 対象のmeetingRoomが存在しない場合
		if (meetingRoom == null) {
			
			System.out.println("=== meeting room not found ===");
		}
		
		return meetingRoom;
	}
	
	public List<ReservableRoom> findReservableRooms(LocalDate date) {
		return reservableRoomRepository.findByReservableRoomId_ReservedDateOrderByReservableRoomId_RoomIdAsc(date);
	}
}
