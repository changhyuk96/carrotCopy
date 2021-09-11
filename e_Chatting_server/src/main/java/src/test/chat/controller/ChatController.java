package src.test.chat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	// Enter 처리 
	@MessageMapping(value= "/chat/enter")
	public void enter(MessageVO message) {
		
		message.setMessage(message.getU_id()+"님이 대화를 시작하셨습니다.");
		couchRepo.subscribe(message);
		template.convertAndSend("/topic/chat/room/"+message.getRoom_id(), message);
	}
	
	@MessageMapping(value="/chat/message")
	public void message(MessageVO message) {
		
		couchRepo.sendMessage(message);
		template.convertAndSend("/topic/chat/room/"+message.getRoom_id(), message);
	}
	
	
	@RequestMapping("/rooms")
	public Object rooms(HttpServletRequest request) {
		
		
		return couchRepo.userChatroomList(request.getParameter("u_id"));
	}
	
	
}
