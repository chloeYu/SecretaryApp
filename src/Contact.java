public class Contact {
	private String company, name, phone, email, addr, location, extra;

	public Contact(String company, String name, String phone, String email, String addr, String location, String extra) {
		this.company = company;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
		this.location = location;
		this.extra = extra;
	}

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	};

	@Override
	public String toString() {
		String str = "회사명: " + company + ", " + "담당자님: " + name + ", " + "전화번호: " + phone + ", " + "메일주소: " + email
				+ ", " + "사이트: " + addr + ", " + "위치: " + location + ", " + "기타 정보: " + extra;
		return str;
	}
}