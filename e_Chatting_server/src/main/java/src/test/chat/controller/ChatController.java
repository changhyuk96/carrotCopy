package src.test.chat.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import src.test.chat.data.CouchbaseRepository;
import src.test.chat.data.MessageVO;

@RestController
@RequiredArgsConstructor
public class ChatController {

	// 특정 Broker로 메세지 전달
	private final SimpMessagingTemplate template;

	@Autowired
	CouchbaseRepository couchRepo;
	
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	// 메세지 매핑.
	@MessageMapping(value="/chat/message")
	public void message(MessageVO message) {
		
		message.setTime(dateTimeFormatter.format(LocalDateTime.now()));

		// Couchbase에 저장
		couchRepo.sendMessage(message);
		
		// 해당 room_id로 전송
		template.convertAndSend("/topic/chat/room/"+message.getRoom_id(), message);
	}
	
	// rooms 목록 
	@GetMapping("/rooms")
	public Object rooms(HttpServletRequest request) {
		
		return couchRepo.userChatroomList(request.getParameter("u_id"));
	}
	
	// 단일 room
	@GetMapping("/room")
	public Object room(MessageVO message) {
		
		return couchRepo.getChatting(message);
	}
	
}
