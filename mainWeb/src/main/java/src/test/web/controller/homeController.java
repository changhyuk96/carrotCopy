package src.test.web.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

	@RequestMapping("/")
	public String home(Model model) {
		
		return "index";
	}
	
	@RequestMapping("/common/common")
	public String common() {
		return "common/common";
	}
	
	
	@RequestMapping("/login")
	public String login() {

		return "login";
	}
	
	@RequestMapping("/signUp")
	public String signUp() {
		return "signUp";
	}
	
	@RequestMapping("/chatRoom")
	public String chatRoom() {
		return "chatRoom";
	}
	
	@RequestMapping("/shop")
	public String shop() {
		SecurityContext sc = SecurityContextHolder.getContext();

		System.out.println(sc.getAuthentication().getName());

		return "shop";
	}
	
	@RequestMapping("/shopDetail")
	public String shopDetail() {
		return "shopDetail";
	}
	
	@RequestMapping("/insertProduct")
	public String insertProduct() {
		return "insertProduct";
	}
	
}
