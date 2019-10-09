package tonyBeans;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class Products {

	private String category;
	private String nameProduct;
	
	public Products() {
		
	}

	
	public Products(String category, String nameProduct) {
		this.category = category;
		this.nameProduct = nameProduct;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	
	

}
