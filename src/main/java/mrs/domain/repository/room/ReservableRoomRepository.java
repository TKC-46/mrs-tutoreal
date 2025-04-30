package mrs.domain.repository.room;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;

@Repository
public interface ReservableRoomRepository extends JpaRepository<ReservableRoom, ReservableRoomId> {
	// ReservableRoomIdのReservedDateを昇順で取得
	List<ReservableRoom> findByReservableRoomId_ReservedDateOrderByReservableRoomId_RoomIdAsc(LocalDate reserveｄDate);
}
