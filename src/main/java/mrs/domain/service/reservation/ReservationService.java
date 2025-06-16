package mrs.domain.service.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.repository.reservation.ReservationRepository;
import mrs.domain.repository.room.ReservableRoomRepository;

@Service
@Transactional
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ReservableRoomRepository reservableRoomRepository;
	
	public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
		return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
	}
	
	
	// 予約処理
	public Reservation reserve(Reservation reservation) {
		// 予約したい会議室のIDを取得
		ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();
		
		// 対象の部屋が予約可能かチェック(悲観ロック)
		ReservableRoom reservalbe = reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId);
		if (reservalbe == null) {
			// そもそも予約不可能
			throw new UnavailableReservationException("入力の日付・部屋の組み合わせでは予約できません。");
		}
		
		// 重複チェック
		// publicがない理由：publicはそもそも外部からアクセスを自由にするという意味、だがoverlap(ローカル変数)はメソッド内の一時的なデータで外からアクセスする必要がないため使えない
		boolean overlap = reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
					.stream().anyMatch(x -> x.overlap(reservation));// 予約可能な会議室を開始時間順に取得し、どれかヒットした部屋を順に重複チェックにかける
		// 重複あり
		if (overlap) {
			// すでに予約済み
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
		}
		
		
		// 予約情報の登録
		reservationRepository.save(reservation);
		return reservation;
		
	}
	
	
	
	/*
	 *  予約取り消し
	 *  @PreAuthorize: Roleによる権限のチェックが可能になる
	 *  EL式で条件検索、@Pでメソッド引数をEL式で参照する名前を指定
	 */
	@PreAuthorize("hasRole('ADMIN') or #reservation.user.userId == principal.user.userId")
	public void cancel(@P("reservation") Reservation reservation) {
		reservationRepository.delete(reservation);
	}
	
	
	// 予約情報の取得
	public Reservation findOne(Integer reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
		// 対象のreservationが存在しない場合の処理,例外を設定予定
		if (reservation == null) {
			System.out.println("=== reservation not found ===");
		}
		
		return reservation;
	}
	
}
