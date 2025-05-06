package mrs.app.room;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mrs.domain.model.ReservableRoom;
import mrs.domain.service.room.RoomService;


@Controller
@RequestMapping("rooms")
public class RoomsController {
	@Autowired
	RoomService roomService;
	
	private static final Logger logger = LoggerFactory.getLogger(RoomsController.class);

	
	// 前日や翌日の予約可能な会議室の一覧
	// @Date：2025-05-06のようなパスをLocalDate型に自動変換
	@RequestMapping(path = "{date}", method = RequestMethod.GET)
	public String listRooms(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date")
			LocalDate date, Model model) { 
		List<ReservableRoom> rooms = roomService.findReservableRooms(date);
		// roomsの中身を確認
		for (ReservableRoom room : rooms) {
            logger.info("会議室情報: roomName={}, roomId={}, reservedDate={}",
                room.getMeetingRoom().getRoomName(),
                room.getReservableRoomId().getRoomId(),
                room.getReservableRoomId().getReservedDate());
        }

		model.addAttribute("rooms", rooms);
		return "room/listRooms";
	}
	
	@GetMapping// 上記のオーバーロード
	public String listRooms(Model model) {
		LocalDate today = LocalDate.now();
		// 予約可能な会議室の一覧
		List<ReservableRoom> rooms = roomService.findReservableRooms(today);
		// ログにデータを表示
		for (ReservableRoom room : rooms) {
            logger.info("会議室情報: roomName={}, roomId={}, reservedDate={}",
                room.getMeetingRoom().getRoomName(),
                room.getReservableRoomId().getRoomId(),
                room.getReservableRoomId().getReservedDate());
        }
		// 画面に渡す情報をModelオブジェクトに渡す
		model.addAttribute("date", today);
		model.addAttribute("rooms", rooms);
		return "room/listRooms";
	}
	
	
	
}
