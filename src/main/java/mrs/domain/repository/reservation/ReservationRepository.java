package mrs.domain.repository.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	public List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId);
}
