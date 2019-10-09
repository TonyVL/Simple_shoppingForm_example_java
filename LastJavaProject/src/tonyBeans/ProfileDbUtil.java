package tonyBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProfileDbUtil {

	private static ProfileDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/midTermProject";

	// Static Singleton method to create ONLY one instance of this class
	public static ProfileDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new ProfileDbUtil();
		}

		return instance;
	}

	private ProfileDbUtil() throws Exception {
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();

		// Lookup connection pool that was created by Tomcat
		// JNDI (Java Naming and Directory Interface) give us access to the objects Tomcat has created
		DataSource theDataSource = (DataSource) context.lookup(jndiName);

		return theDataSource;
	}

	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();

		return theConn;
	}

	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}

	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public List<Profile> getProfiles() throws Exception {

		List<Profile> profiles = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = getConnection();

			String sql = "select * from midTerm order by lastname";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("firstname");
				String lastName = myRs.getString("lastname");
				String gender = myRs.getString("gender");
				String phone = myRs.getString("phone");
				String address = myRs.getString("address");
				String country = myRs.getString("country");
				String email = myRs.getString("email");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String monthlydiscemail = myRs.getString("monthlydiscemail");
				String weeklypromotionemail = myRs.getString("weeklypromotionemail");
				String typeprofile = myRs.getString("typeprofile");				
				
				// create new profile object
				Profile tempProfile = new Profile(id, firstName, lastName, gender, phone, address, country, email, username,password, 
									monthlydiscemail, weeklypromotionemail, typeprofile);
				
				// add it to the list of profiles
				profiles.add(tempProfile);
			}

			return profiles;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	// Get the profile by profile ID from database .................
	// This method is calling from loadProfile(int profileId) in ProfileController class 
	// loadProfile(int profileId) will make returned profile available in memory 
	// for other pages as a part of servlet data
	
	public Profile getProfile(int profileId) throws Exception {

			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;

			try {
				myConn = getConnection();

				String sql = "select * from midTerm where id=?";

				myStmt = myConn.prepareStatement(sql);

				// Set params
				myStmt.setInt(1, profileId);

				myRs = myStmt.executeQuery();

				Profile theProfile = null;

				// Retrieve data from result set row
				if (myRs.next()) {
					int id = myRs.getInt("id");
					String firstName = myRs.getString("firstname");
					String lastName = myRs.getString("lastname");
					String gender = myRs.getString("gender");
					String phone = myRs.getString("phone");
					String address = myRs.getString("address");
					String country = myRs.getString("country");
					String email = myRs.getString("email");
					String username = myRs.getString("username");
					String password = myRs.getString("password");
					String monthlydiscemail = myRs.getString("monthlydiscemail");
					String weeklypromotionemail = myRs.getString("weeklypromotionemail");
					String typeprofile = myRs.getString("typeprofile");	
					
					theProfile = new Profile(id, firstName, lastName, gender, phone, address, country, email, username,password, 
										monthlydiscemail, weeklypromotionemail, typeprofile);
					
				} else {
					throw new Exception("Could not find profile id: " + profileId);
				}

				return theProfile;
			} finally {
				close(myConn, myStmt, myRs);
			}
		}
	//..............................................................
	
	public void addProfile(Profile theProfile) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into midTerm (id, firstname, lastname, gender, phone, address, country,"
					+ "email, username, password, monthlydiscemail, weeklypromotionemail,typeprofile) "
					+ "values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, theProfile.getId());
			myStmt.setString(2, theProfile.getFirstName());
			myStmt.setString(3, theProfile.getLastName());
			myStmt.setString(4, theProfile.getGender());
			myStmt.setString(5, theProfile.getPhone());
			myStmt.setString(6, theProfile.getAddress());
			myStmt.setString(7, theProfile.getCountry());
			myStmt.setString(8, theProfile.getEmail());
			myStmt.setString(9, theProfile.getUsername());
			myStmt.setString(10, theProfile.getPassword());
			myStmt.setString(11, theProfile.getMonthlyDiscEmail());
			myStmt.setString(12, theProfile.getWeeklyPromotionEmail());
			myStmt.setString(13, theProfile.getTypeProfile());

			myStmt.execute();
		} finally {
			close(myConn, myStmt);
		}

	}

	//..............................................................
	public void updateProfile(Profile theProfile) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update midTerm " + " set firstname=?, lastname=?, gender=?, phone=?, address=?, country=?,"
					+ "email=?, username=?, password=?, monthlydiscemail=?, weeklypromotionemail=?, typeprofile=? " + " where clientid=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theProfile.getFirstName());
			myStmt.setString(2, theProfile.getLastName());
			myStmt.setString(3, theProfile.getGender());
			myStmt.setString(4, theProfile.getPhone());
			myStmt.setString(5, theProfile.getAddress());
			myStmt.setString(6, theProfile.getCountry());
			myStmt.setString(7, theProfile.getEmail());
			myStmt.setString(8, theProfile.getUsername());
			myStmt.setString(9, theProfile.getPassword());
			myStmt.setString(10, theProfile.getMonthlyDiscEmail());
			myStmt.setString(11, theProfile.getWeeklyPromotionEmail());
			myStmt.setString(12, theProfile.getTypeProfile());
			
			myStmt.setInt(13, theProfile.getId());

			myStmt.execute();
		} finally {
			close(myConn, myStmt);
		}

	}
	//..............................................................
	
	public void deleteProfile(int profileId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from midTerm where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, profileId);

			myStmt.execute();
		} finally {
			close(myConn, myStmt);
		}
	}

	// Search method ----------------------------------------------------------------
	public List<Profile> searchProfiles(String theSearchName) throws Exception {

		// 1- Result list
		List<Profile> profiles = new ArrayList<>();

		// 2- Clean attributes
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
//		int profileId;

		try {

			// 3- Get connection to database
			myConn = dataSource.getConnection();

			// 4- Only search by name if theSearchName is not empty
			if (theSearchName != null && theSearchName.trim().length() > 0) {

				// 5- Create sql to search for profiles by name
				String sql = "select * from midTerm where lower(firstname) like ? or lower(firstname) like ?";

				// 6- Create prepared statement
				myStmt = myConn.prepareStatement(sql);

				// 7- Set params
				String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
				myStmt.setString(1, theSearchNameLike);
				myStmt.setString(2, theSearchNameLike);

			} else {
				// 8- Create sql to get all profiles
				String sql = "select * from midTerm order by lastname";

				// 9- Create prepared statement
				myStmt = myConn.prepareStatement(sql);
			}

			// 10- Execute statement
			myRs = myStmt.executeQuery();

			// 11- Retrieve data from result set row
			while (myRs.next()) {

				// 12- Retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("firstname");
				String lastName = myRs.getString("lastname");
				String gender = myRs.getString("gender");
				String phone = myRs.getString("phone");
				String address = myRs.getString("address");
				String country = myRs.getString("country");
				String email = myRs.getString("email");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String monthlydiscemail = myRs.getString("monthlydiscemail");
				String weeklypromotionemail = myRs.getString("weeklypromotionemail");
				String typeprofile = myRs.getString("typeprofile");				
				
				// 13- Create new profile object
				Profile tempProfile = new Profile(id, firstName, lastName, gender, phone, address, country, email, username,password, 
									monthlydiscemail, weeklypromotionemail, typeprofile);
				
				// 14- Add it to the list of profiles
				profiles.add(tempProfile);
			}

			return profiles;
		} finally {

			// 15- Clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	// ------------------------------------------------------------------------------

	
	
	
	
}
