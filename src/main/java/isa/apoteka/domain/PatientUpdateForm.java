package isa.apoteka.domain;

public class PatientUpdateForm {

	private Long id;
	
	private String username;

	private String oldPass;
	
	private String newPass;

	private String name;

	private String surname;
	
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldpass() {
		return oldPass;
	}
	public void setOldPass(String password) {
		this.oldPass = password;
	}

	public void setNewPass(String password) {
		this.newPass = password;
	}
	
	public String getNewPass() {
		return newPass;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstname) {
		this.name = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String lastname) {
		this.surname = lastname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}