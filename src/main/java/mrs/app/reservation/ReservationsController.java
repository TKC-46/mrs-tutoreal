package mrs.app.reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.model.RoleName;
import mrs.domain.model.User;
import mrs.domain.service.reservation.AlreadyReservedException;
import mrs.domain.service.reservation.ReservationService;
import mrs.domain.service.reservation.UnavailableReservationException;
import mrs.domain.service.room.RoomService;


@Controller
@RequestMapping("reservations/{date}/{roomId}")
public class ReservationsController {
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	ReservationService reservationService;
	
	// 各リクエストのmodelに格納するオブジェクト作成
	@ModelAttribute
	ReservationForm setUpForm() {
		ReservationForm form = new ReservationForm();
		// デフォルト値
		form.setStartTime(LocalTime.of(9, 0));
		form.setEndTime(LocalTime.of(10, 0));
		return form;
	}
	
	
	// @RequestMapping(mehtod = RequestMethod.GET)の省略形こちらはより詳細の設定する際に使う
	@GetMapping
	public String reserveForm(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)// YYYY-MM-DD形式で表示
			@PathVariable("date") LocalDate date,
				@PathVariable("roomId") Integer roomId, Model model) {
		// リクエストの会議室を取得
		MeetingRoom meetingRoom = roomService.findMeetingRoom(roomId);
		// ある会議室のある日付の予約をまとめて取り出す
		ReservableRoomId reservableRoomId = new ReservableRoomId(roomId, date);
		// 対象の会議室・日付の予約一覧
		List<Reservation> reservations = reservationService.findReservations(reservableRoomId);
		// 0:00 〜 23:30 までの30分刻みの時刻リスト
		List<LocalTime> timeList = Stream.iterate(LocalTime.of(0, 0),// 00：00からリスト化
										time -> time.plusMinutes(30))// 30分刻みでリストに追加
										.limit(24 * 2).collect(Collectors.toList());// 最大48個の時間を配列化＝＞[00:00, 00:30 ～ 23:30]計48個
		
		model.addAttribute("room", meetingRoom);
		model.addAttribute("reservations", reservations);
		model.addAttribute("timeList", timeList);
		model.addAttribute("user", dummyUser());
		return "reservation/reserveForm";
	}
	
	// private メソッドなのでこのクラス内ではインスタンス化せず使える
	private User dummyUser() {
		User user = new User();
		user.setUserId("taro-yamada");
		user.setFirstName("太郎");
		user.setLastName("山田");
		user.setRoleName(RoleName.USER);
		return user;
	}
	
	// 予約処理
	@PostMapping
	public String reserve(@Validated ReservationForm form, BindingResult bindingResult,
					@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
					@PathVariable("roomId") Integer roomId, Model model) {
		
		
		if (bindingResult.hasErrors()) {
			return reserveForm(date, roomId, model);
		}
		
		
		ReservableRoom reservableRoom = new ReservableRoom(new ReservableRoomId(roomId, date));
		Reservation reservation = new Reservation();
		reservation.setStartTime(form.getStartTime());
		reservation.setEndTime(form.getStartTime());
		reservation.setReservableRoom(reservableRoom);
		reservation.setUser(dummyUser());
			
		try {
			reservationService.reserve(reservation);
		} catch (UnavailableReservationException | AlreadyReservedException e) {
			model.addAttribute("error", e.getMessage());
			return reserveForm(date, roomId, model);
		}
		
		return "redirect:/reservations/{date}/{roomId}";
	}
	
	

}
