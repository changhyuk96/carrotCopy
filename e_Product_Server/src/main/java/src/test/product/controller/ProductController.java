package src.test.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import src.test.product.data.ProductAttachFileDTO;
import src.test.product.data.ProductDTO;
import src.test.product.data.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	// 상품 목록 조회 	->	GET /products
	@GetMapping("/products")
	public Object getProducts() {
		
		return productService.getProductList();
	}
	
	// 단일 상품 조회	->	GET /product/{p_id}
	@GetMapping("/product/{p_id}")
	public Object getProduct(@PathVariable String p_id) {
		
		return productService.getProduct(p_id);
	}
	
	// 상품 등록 		->	POST /product 
	@PostMapping("/product")
	public Object createProduct(ProductDTO productDTO) {
		
		int result = productService.createProduct(productDTO);
		
		return result >= 1 ? "상품이 등록되었습니다." : "상품 등록에 실패했습니다.";
	}
	
	// 상품 업데이트	->	PUT	/product/{p_id}
	@PutMapping("/product/{p_id}")
	public Object updateProduct(ProductDTO productDTO) {
		
		int result =productService.updateProduct(productDTO);
		return result >= 1 ? "수정되었습니다." : "수정에 실패했습니다.";
	}
	
	// 상품 삭제		->	DELETE /product/{p_id}
	@DeleteMapping("/product/{p_id}")
	public Object deleteProduct(@PathVariable String p_id) {
		int result =productService.deleteProduct(p_id);
		return result >= 1 ? "삭제되었습니다." : "삭제에 실패했습니다.";
	}
	
	
	// 첨부파일
	@PostMapping("/product/file")
	public Object insertProductFile(ProductAttachFileDTO productAttchFileDTO, @RequestParam("files") MultipartFile[] uploadFile){
		
		int result = 0;
		for(MultipartFile multiFile : uploadFile) {
			
			if(!multiFile.isEmpty()) {
				try {
				productAttchFileDTO.setP_uuid(UUID.randomUUID().toString());
				productAttchFileDTO.setP_originalName(multiFile.getOriginalFilename());
				
				File file = new File(productAttchFileDTO.getP_uuid()+"_"+productAttchFileDTO.getP_originalName() );
				multiFile.transferTo(file);
				result = productService.insertFile(productAttchFileDTO);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		
		return result >= 1 ? "success" : "failed";
	}
}
