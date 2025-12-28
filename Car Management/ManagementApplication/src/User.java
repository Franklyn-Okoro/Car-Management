

public class User {
	private String accName;
	private String password;
	private String role;

	public User(String name, String password, String role) {
		// TODO Auto-generated constructor stub
		this.accName = name;
		this.password = password;
		this.role = role;
	}
	
	public String getName() {
		return this.accName;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public String toString() {
		return this.accName + " | "    + this.role;
	}
}
