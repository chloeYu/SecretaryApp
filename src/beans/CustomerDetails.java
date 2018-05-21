package beans;
import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerDetails implements Serializable {
	private  String name,add, phno,email;
	char[] pass;

	public CustomerDetails(String name, String add, String phno, String email,char[] pass) {
		super();
		this.name = name;
		this.add = add;
		this.phno = phno;
		this.email = email;
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char[]  getPass() {
		return pass;
	}

	public void setPass(char[] pass) {
		this.pass = pass;
	}
	

}
