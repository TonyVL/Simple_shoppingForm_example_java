package tonyBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean

public class Profile {

	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private String phone;
	private String address;
	private String country;
	private String email;
	private String username;
	private String password;
	private String monthlyDiscEmail;
	private String weeklyPromotionEmail;
	private String typeProfile;
	
	private String selectInformation;




	public Profile() {
	}

	public Profile(String selectInformation) {
		
		this.selectInformation = selectInformation;
	}
	

	public String getSelectInformation() {
		return selectInformation;
	}
	
	

	public void setSelectInformation(String selectInformation) {
		this.selectInformation = selectInformation;
	}

	
	
	public Profile(int id, String firstName, String lastName, String gender, String phone, String address,
			String country, String email, String username, String password, String monthlyDiscEmail,
			String weeklyPromotionEmail, String typeProfile) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.country = country;
		this.email = email;
		this.username = username;
		this.password = password;
		this.monthlyDiscEmail = monthlyDiscEmail;
		this.weeklyPromotionEmail = weeklyPromotionEmail;
		this.typeProfile = typeProfile;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMonthlyDiscEmail() {
		return monthlyDiscEmail;
	}


	public void setMonthlyDiscEmail(String monthlyDiscEmail) {
		this.monthlyDiscEmail = monthlyDiscEmail;
	}


	public String getWeeklyPromotionEmail() {
		return weeklyPromotionEmail;
	}


	public void setWeeklyPromotionEmail(String weeklyPromotionEmail) {
		this.weeklyPromotionEmail = weeklyPromotionEmail;
	}


	public String getTypeProfile() {
		return typeProfile;
	}


	public void setTypeProfile(String typeProfile) {
		this.typeProfile = typeProfile;
	}

	@Override
	public String toString() {
		return "Profile [selectInformation=" + selectInformation + "]";
	}
	
	// Method must be public and return a string which is the name of view	
	public String selectInformationProfile() {
		if (selectInformation != null && selectInformation.equals("ConfirmProfile")) {
			return "shopping";
		} else {
			return "update-profile-form";
		}
	}

	
	//custom validation username
			public void validateTheUserName(FacesContext contex, 
											UIComponent component, 
											Object value)throws ValidatorException{
				if (value == null) { return; }
				String data = value.toString();
				// Course code must start with S and four digits.... if not, throw exception
				if(!data.startsWith("IPD17")) {
					FacesMessage message = new FacesMessage("User Name must start with IPD17####");
					throw new ValidatorException(message);
				}
				
			}

	
}