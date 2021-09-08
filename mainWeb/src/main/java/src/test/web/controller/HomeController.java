package src.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {
		
		return "index";
	}
	
	@RequestMapping("/common/common")
	public String common() {
		return "common/common";
	}
	
	@RequestMapping("/chatRoom")
	public String chatRoom() {
		return "chatRoom";
	}
	
	@RequestMapping("/login")
	public String login() {

		return "login";
	}
	
	@RequestMapping("/signUp")
	public String signUp() {
		return "signUp";
	}

	
}
