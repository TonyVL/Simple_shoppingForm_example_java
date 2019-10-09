package tonyBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ProfileController {

	private List<Profile> profiles;
	private ProfileDbUtil profileDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());

	// 1- Add theSearchName attribute -----------------------------------------------
	private String theSearchName;
	// ------------------------------------------------------------------------------

	// 2- theSearchName getter and setter -------------------------------------------
	public String getTheSearchName() {
		return theSearchName;
	}

	public void setTheSearchName(String theSearchName) {
		this.theSearchName = theSearchName;
	}
	// ------------------------------------------------------------------------------
	
	public List<Profile> getProfiles() {
		return profiles;
	}
	
	
	public ProfileController() throws Exception {
		
		// Initialize instance variables
		profiles = new ArrayList<>();
		profileDbUtil = ProfileDbUtil.getInstance();
	}

	// 3- Add logic to loadStudents() method which will be called when page is reloaded
	// ------------------------------------------------------------------------------

	public void loadProfiles() {

		logger.info("\n\n-----Loading Customers");
		logger.info("theSearchName = " + theSearchName);
		//profiles.clear();

		try {
			
			// ------------------------------------------------------------------------------
						if (theSearchName != null && theSearchName.trim().length() > 0) {
							// Search for client by name
							profiles = profileDbUtil.searchProfiles(theSearchName);
						}
						// ------------------------------------------------------------------------------

						else {
							// Get all profiles from database by ProfileDbUtil class
							profiles = profileDbUtil.getProfiles();
						}
			

		} catch (Exception exc) {
			// Send this to server logs
			logger.log(Level.SEVERE, "Error loading profiles", exc);

			// Add error message for JSF page
			addErrorMessage(exc);
		} finally {
			// Reset the search info
			theSearchName = null;
		} 
		
	}

	public String addProfile(Profile theProfile) {

		logger.info("\n\n-------------------- Adding profile: " + theProfile);

		try {

			// add profile to the database
			profileDbUtil.addProfile(theProfile);

		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding profile", exc);

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		return "list-profiles?faces-redirect=true";
	}

	// UPDATE ---------------------------------------------------------------------------------------

	// Get selected profile and make it available in memory 
	// for other pages as a part of servlet data
	// This method is calling from list-profiles.xhtml
	public String loadProfile(int profileId) {

		logger.info("\n\n-------------------- loading profile: " + profileId);

		try {
			// 1- get profile from database
			Profile theProfile = profileDbUtil.getProfile(profileId);

			// 2- Create an externalContext object ........................................
			/*
			 * ExternalContext can be consider as a memory space we use 
			 * to store data and have access to it
			 * 
			 * FacesContext contains all of the per-request state information 
			 * related to the processing of a single JavaServer Faces request, 
			 * and the rendering of the corresponding response.
			 * 
			 * A FacesContext instance is associated with a particular request 
			 * at the beginning of request processing, 
			 * by a call to the getFacesContext() method of the 
			 * FacesContextFactory instance associated with the 
			 * current web application. The instance remains active until 
			 * its release() method is called, after which no further 
			 * references to this instance are allowed. 
			 * While a FacesContext instance is active, 
			 * it must not be referenced from any thread other than 
			 * the one upon which the servlet container executing 
			 * this web application utilizes for the processing of 
			 * this request.
			*/
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			// 3- Return request object Map attribute from ExternalContext object
			Map<String, Object> requestMap = externalContext.getRequestMap();

			// 4- Put selected profile to request object Map attributes
			requestMap.put("profile", theProfile);
			//.............................................................................
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading profile id:" + profileId, exc);

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		// 5- Go to the next page to update this selected profile
		// By calling update-profile-form.xhtml.xtml "externalContext" will be forwarded to that page, and
		// update-profile-form will be populated with requestMap object
		return "update-profile-form.xhtml";
	}

	// This method is calling from update-profile-form.xhtml
	public String updateProfile(Profile theProfile) {

		logger.info("\n\n-------------------- updating profile: " + theProfile);

		try {

			// 1- Update profile in the database
			profileDbUtil.updateProfile(theProfile);

		} catch (Exception exc) {
			// 2- Send this to server logs
			logger.log(Level.SEVERE, "Error updating profile: " + theProfile, exc);

			// 3- Add error message for JSF page
			addErrorMessage(exc);

			return null;
		}
		// redirect: Browser URL will be updated. It is like sending an other GET request for a specific page
		// forward:  Browser URL will not be updated
		return "list-profiles?faces-redirect=true";
	}
	// -----------------------------------------------------------------------------------------------
	
	//method for deleting Profile
	public String deleteProfile(int profileId) {

		logger.info("Deleting midTermProject id: " + profileId);

		try {

			// delete the student from the database
			profileDbUtil.deleteProfile(profileId);

		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting profile id: " + profileId, exc);

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		return "list-profiles";
	}
	
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
