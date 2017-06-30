package com.travix.medusa.busyflights.domain.busyflights;

/* Comparable interface implemented to sort the List od response*/
public class BusyFlightsResponse implements Comparable<BusyFlightsResponse>{
	private String airline;
	private String supplier;
	private double fare;
	private String departureAirportCode;
	private String destinationAirportCode;
	private String departureDate;
	private String arrivalDate;
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getDepartureAirportCode() {
		return departureAirportCode;
	}
	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}
	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}
	public void setDestinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	/* Method added to sortlist according to fare value*/
	@Override
	public int compareTo(BusyFlightsResponse flightResponse){  
		if(fare==flightResponse.fare)  
		return 0;  
		else if(fare>flightResponse.fare)  
		return 1;  
		else  
		return -1;  
		}
	@Override
	public String toString() {
		return "BusyFlightsResponse [airline=" + airline + ", supplier=" + supplier + ", fare=" + fare + "]";
	}  
	
	
}
