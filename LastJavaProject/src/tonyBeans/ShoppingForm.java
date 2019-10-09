package tonyBeans;

import javax.faces.application.FacesMessage;
//0- be careful to import ManagedBean from face NOT javax.annotation
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.NoneScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

//1- add @ManagedBean annotation
@ManagedBean
//@SessionScoped
//@RequestScoped
//@ViewScoped
@ApplicationScoped
public class ShoppingForm {
	
	public ShoppingForm() {
		loadSampleData();
	}
	
	private List<Products> categoriesArrayList;

	
	public void loadSampleData() {
		categoriesArrayList = new ArrayList<>();
		
		categoriesArrayList.add(new Products("Mobile", "Galaxy s8+"));
		categoriesArrayList.add(new Products("Mobile", "Iphone6"));
		categoriesArrayList.add(new Products("Mobile", "Note 4+"));
		categoriesArrayList.add(new Products("Mobile", "Iphone 3"));
		categoriesArrayList.add(new Products("Software", "Adobe Photoshop"));
		categoriesArrayList.add(new Products("Software", "Windows 8"));
		categoriesArrayList.add(new Products("Software", "mac OS"));
		categoriesArrayList.add(new Products("Computer", "cutom"));
		categoriesArrayList.add(new Products("Computer", "HP"));
		categoriesArrayList.add(new Products("Tablet", "Samsung"));
		categoriesArrayList.add(new Products("Watch", "Rolex"));
		categoriesArrayList.add(new Products("Watch", "Omega"));
		categoriesArrayList.add(new Products("Television", "Samsung"));
		categoriesArrayList.add(new Products("Television", "Sharp"));

	}
	

	public List<Products> getcategoriesArrayList() {
		return categoriesArrayList;
	}

	

}
