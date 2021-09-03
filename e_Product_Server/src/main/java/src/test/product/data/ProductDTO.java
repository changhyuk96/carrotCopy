package src.test.product.data;

import lombok.Data;

@Data
public class ProductDTO {
	private String p_id;
	private String p_title;
	private String p_content;
	private String p_date;
	private String p_username;
	private int p_price;
}
