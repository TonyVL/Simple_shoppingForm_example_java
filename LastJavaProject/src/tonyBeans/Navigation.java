package tonyBeans;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class Navigation {

	private String nextPage;

	public Navigation() {}

	
	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}


	//Method shall return a string which name is the name of view
	public String startNavigation() {
		if (nextPage!=null && nextPage.contentEquals("Confirm")) return "shopping";
		else if (nextPage=="") return "add-profile-form";
		else return "add-profile-form";
	}
	
}
