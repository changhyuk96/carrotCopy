package src.test.web.controller;

import java.net.URI;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import src.test.web.util.ServiceUtil;

@RequestMapping("/products")
@Controller
public class ProductController {
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	ObjectMapper mapper;
	
	JSONParser parser = new JSONParser();

	
	@RequestMapping("/shop")
	public Object shop(Model model, HttpServletRequest request, HttpServletResponse response){
		
		URI url = URI.create("http://localhost:8090/api/product/products");
		
		ResponseEntity<?> responseEntity = serviceUtil.getResponseEntity(request, response, url, HttpMethod.GET, MediaType.APPLICATION_FORM_URLENCODED);
		
		try {
			JSONArray json = (JSONArray)parser.parse(responseEntity.getBody().toString());
			
			model.addAttribute("productList", json);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}

		return "/products/shop";
	}
	
	@RequestMapping("/productDetail")
	public String shopDetail(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		URI url = URI.create("http://localhost:8090/api/product/product/"+request.getParameter("p_id"));
		
		ResponseEntity<?> responseEntity = serviceUtil.getResponseEntity(request, response, url, HttpMethod.GET, MediaType.APPLICATION_FORM_URLENCODED);
		
		try {
			
			JSONObject json = (JSONObject) parser.parse(responseEntity.getBody().toString());
			
			model.addAttribute("productDetail", json);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/products/productDetail";
	}
	
	@GetMapping("/insertProduct")
	public String insertProduct() {
		return "/products/insertProduct";
	}
}
