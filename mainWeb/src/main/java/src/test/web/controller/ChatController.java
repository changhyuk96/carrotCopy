package src.test.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import src.test.web.util.ServiceUtil;

@RequestMapping("/chats")
@Controller
public class ChatController {
	
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	ServiceUtil serviceUtil; 
	
	@GetMapping("/chatRoom")
	public Object chatRoom(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		model.addAttribute("room_id", UUID.randomUUID());
		model.addAttribute("u_id", request.getParameter("u_id"));
		model.addAttribute("u_nickname", request.getParameter("u_nickname"));	
		model.addAttribute("u_id_target", request.getParameter("u_id_target"));	
		
		return "/chats/chatRoom";
	}
	
}
