package src.test.web.controller;

import java.net.URI;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	JSONParser parser = new JSONParser();
	
	@GetMapping("/chatRoom")
	public Object chatRoom(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		model.addAttribute("u_id", request.getParameter("u_id"));
		
		URI url = URI.create("http://localhost:8090/api/chat/rooms?u_id="+request.getParameter("u_id"));
		
		ResponseEntity<?> responses = serviceUtil.getResponseEntity(request, response, url, HttpMethod.GET, MediaType.APPLICATION_JSON);
		
		try {
			
			System.out.println(responses.getBody().toString().replaceAll("\\\\\"", "\""));
			System.out.println(responses.getBody().toString().replaceAll("\\\\\"", "\"").replaceAll("\"\\{", "{").replaceAll("\\}\"", "}"));

			
			JSONArray array = (JSONArray) parser.parse(responses.getBody().toString().replaceAll("\\\\\"", "\"").replaceAll("\"\\{", "{").replaceAll("\\}\"", "}"));
			
			model.addAttribute("chatList", array);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/chats/chatRoom";
	}
	
	@GetMapping("/chatting")
	public String chatting(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		URI url = URI.create("http://localhost:8090/api/chat/room?room_id="+request.getParameter("room_id"));
		
		ResponseEntity<?> responses = serviceUtil.getResponseEntity(request, response, url, HttpMethod.GET, MediaType.APPLICATION_JSON);
		
		try {
			JSONArray json = (JSONArray)parser.parse(responses.getBody().toString());
			
			model.addAttribute("chatHistory", json);
			model.addAttribute("u_id", request.getParameter("u_id"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/chats/chatting";
	}
	
}
