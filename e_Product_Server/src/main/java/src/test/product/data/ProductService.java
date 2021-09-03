package src.test.product.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO dao;
	
	public List<ProductDTO> getProductList(){
		return dao.getProductList();
	}
	
	public ProductDTO getProduct(String p_id) {
		return dao.getProduct(p_id);
	}
	
	public int createProduct(ProductDTO productDTO) {
		return dao.createProduct(productDTO);
	}
	
	public int updateProduct(ProductDTO productDTO) {
		return dao.updateProduct(productDTO);
	}
	
	public int deleteProduct(String p_id) {
		return dao.deleteProduct(p_id);
	}
	
	// 첨부 파일
	public int insertFile(ProductAttachFileDTO productAttachFileDTO) {
		return dao.insertFile(productAttachFileDTO);
	}
	public int updateFile(ProductAttachFileDTO productAttachFileDTO) {
		return dao.updateFile(productAttachFileDTO);
	}
	public int deleteFile(String p_id, String p_uuid) {
		return dao.deleteFile(p_id, p_uuid);
	}
}
