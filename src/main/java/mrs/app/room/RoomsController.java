package mrs.app.room;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mrs.domain.model.ReservableRoom;
import mrs.domain.service.room.RoomService;


@Controller
@RequestMapping("rooms")
public class RoomsController {
	@Autowired
	RoomService roomService;
	
	
	@GetMapping
	public String listRooms(Model model) {
		LocalDate today = LocalDate.now();
		// 予約可能な会議室の一覧
		List<ReservableRoom> rooms = roomService.findReservableRooms(today);
		// 画面に渡す情報をModelオブジェクトに渡す
		model.addAttribute("date", today);
		model.addAttribute("rooms", rooms);
		return "room/listRooms";
	}
}
