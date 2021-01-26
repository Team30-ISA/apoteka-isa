package isa.apoteka.domain;

public class PatientUpdateForm {

	private Long id;
	
	private String username;

	private String oldpass;
	
	private String newpass;

	private String name;

	private String surname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldpass() {
		return oldpass;
	}
	public void setOldpass(String password) {
		this.oldpass = password;
	}

	public void setNewpass(String password) {
		this.newpass = password;
	}
	
	public String getNewpass() {
		return newpass;
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}