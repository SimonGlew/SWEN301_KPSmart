package users;

public class StaffMember {
	String username;
	String encodedPassword;
	boolean isManager;

	public StaffMember(String username, String encodedPassword, boolean isManager){
		this.username = username;
		this.encodedPassword = encodedPassword;
		this.isManager = isManager;
	}

	public StaffMember(){

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String password) {
		this.encodedPassword = password;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		return "StaffMember [username=" + username + ", encodedPassword=" + encodedPassword + ", isManager=" + isManager
				+ "]";
	}




}
