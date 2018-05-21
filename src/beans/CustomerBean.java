package beans;

import java.io.Serializable;

public class CustomerBean implements Serializable {
 private String name;
 private String sex;
 private String address;
 private int mobileno;
 String  email;
 String password;
public CustomerBean(String name, String sex, String address, String password,
		String email, int mobileno) {
	super();
	this.name = name;
	this.sex = sex;
	this.address = address;
	
	this.password = password;
	this.email = email;
	this.mobileno = mobileno;
	
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
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