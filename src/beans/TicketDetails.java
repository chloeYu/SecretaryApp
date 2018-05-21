package beans;


import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TicketDetails implements Serializable {
	private String name;
	private String address;
	private String mobileno;
	private String source;
	private String destination;
	private String busname;
	private String route;
	private String bustype;
	private int seat;
	private int ticketnumber;
	private String arrivaltime;
	private String deperaturetime;
	private Date date;
	
	public TicketDetails(String name, String address, String mobileno,
			String source, String destination, String busname, String route,
			String bustype, int seat, int ticketnumber, String arrivaltime,
			String deperaturetime, Date date) {
		super();
		this.name = name;
		this.address = address;
		this.mobileno = mobileno;
		this.source = source;
		this.destination = destination;
		this.busname = busname;
		this.route = route;
		this.bustype = bustype;
		this.seat = seat;
		this.ticketnumber = ticketnumber;
		this.arrivaltime = arrivaltime;
		this.deperaturetime = deperaturetime;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBusname() {
		return busname;
	}

	public void setBusname(String busname) {
		this.busname = busname;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getBustype() {
		return bustype;
	}

	public void setBustype(String bustype) {
		this.bustype = bustype;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getTicketnumber() {
		return ticketnumber;
	}

	public void setTicketnumber(int ticketnumber) {
		this.ticketnumber = ticketnumber;
	}

	public String getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getDeperaturetime() {
		return deperaturetime;
	}

	public void setDeperaturetime(String deperaturetime) {
		this.deperaturetime = deperaturetime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
