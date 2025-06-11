package mrs.domain.repository.room;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;

@Repository
public interface ReservableRoomRepository extends JpaRepository<ReservableRoom, ReservableRoomId> {
	
	/*
	 *  悲観ロック： 他のユーザーと会議室の登録が同じタイミングで予約するのを防ぐ、
	 *  		 （排他処理）のため予約可能会議室IDを取得してロックをかける
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	ReservableRoom findOneForUpdateByReservableRoomId(ReservableRoomId reservableRoomId);
	
	
	// 予約可能な部屋IDを昇順で取得
	List<ReservableRoom> findByReservableRoomId_ReservedDateOrderByReservableRoomId_RoomIdAsc(LocalDate reserveｄDate);
}
