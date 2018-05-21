package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BusDetails implements Serializable{
	private String source;
	private String destination;
	private String busname;
	private String busroute;
	private String bustype;
	private int seats;
	private String arrivaltime;
	private String deperaturetime;
	private int seatbook;
	public BusDetails()
	{
		
	}
	public BusDetails(String source, String destination, String busname,
			String busroute, String bustype, int seats, String arrivaltime,
			String deperaturetime, int seatbook) {
		super();
		this.source = source;
		this.destination = destination;
		this.busname = busname;
		this.busroute = busroute;
		this.bustype = bustype;
		this.seats = seats;
		this.arrivaltime = arrivaltime;
		this.deperaturetime = deperaturetime;
		this.seatbook = seatbook;
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
	public String getBusroute() {
		return busroute;
	}
	public void setBusroute(String busroute) {
		this.busroute = busroute;
	}
	public String getBustype() {
		return bustype;
	}
	public void setBustype(String bustype) {
		this.bustype = bustype;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
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
	public int getSeatbook() {
		return seatbook;
	}
	public void setSeatbook(int seatbook) {
		this.seatbook = seatbook;
	}
	
}
