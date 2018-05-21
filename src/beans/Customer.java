package beans;

public class Customer {
private String name;
private char sex;
private String address;
private int mobileno;
private String email;
private String password;
public Customer(){
	
}
public Customer(String name, char sex, String address, int mobileno,
		String email, String password) {
	super();
	this.name = name;
	this.sex = sex;
	this.address = address;
	this.mobileno = mobileno;
	this.email = email;
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public char getSex() {
	return sex;
}
public void setSex(char sex) {
	this.sex = sex;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getMobileno() {
	return mobileno;
}
public void setMobileno(int mobileno) {
	this.mobileno = mobileno;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


}
