package src.test.product.data;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDAO {

	public List<ProductDTO> getProductList();
	public ProductDTO getProduct(String p_id);
	public int createProduct(ProductDTO productDTO);
	public int updateProduct(ProductDTO productDTO);
	public int deleteProduct(String p_id);
	
	// 첨부파일
	public int insertFile(ProductAttachFileDTO productAttachFileDTO);
	public int updateFile(ProductAttachFileDTO productAttachFileDTO);
	public int deleteFile(String p_id, String p_uuid);
}
