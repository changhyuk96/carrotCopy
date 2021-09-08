package src.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products")
@Controller
public class ProductController {

	
	@RequestMapping("/shop")
	public String shop() {

		return "/products/shop";
	}
	
	@RequestMapping("/productDetail")
	public String shopDetail() {
		return "/products/productDetail";
	}
	
	@GetMapping("/insertProduct")
	public String insertProduct() {
		return "/products/insertProduct";
	}
}
