package web.minda.project.dto;

public class UserCreatedEvent {
	
	public Long userID;
	
	public String firstName;
	
	public String contact;
	
	public String email;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserCreatedEvent(Long userID, String firstName, String contact, String email) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.contact = contact;
		this.email = email;
	}

	public UserCreatedEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
