package com.travix.medusa.busyflights.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.httpclient.GetServiceResponse;
import com.travix.medusa.busyflights.utility.FlightSearchUtility;

@Service("BusyFlightService")
public class BusyFlightServiceImpl implements BusyFlightService{
	
	@Autowired
	GetServiceResponse clientService;
	private static String flightData;
	
	private static final String CRAZYAIR = "Crazy Air";
	private static final String TOUGHAIR = "Tough Jet";
	List<CrazyAirResponse> crazyAirList;
	List<ToughJetResponse> toughJetList;
	public String getAllFlights(BusyFlightsRequest busyFlightsRequest) {
		
		CrazyAirRequest crazyRequest = new CrazyAirRequest();
		crazyRequest.setOrigin(busyFlightsRequest.getOrigin());
		crazyRequest.setDestination(busyFlightsRequest.getDestination());
		crazyRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
		crazyRequest.setReturnDate(busyFlightsRequest.getReturnDate());
		crazyRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());

		ToughJetRequest toughJetRequest = new ToughJetRequest();
		toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
		toughJetRequest.setTo(busyFlightsRequest.getDestination());
		toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
		toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
		toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
	
						
		try {

			if (null != (clientService.sendGetCrazy(crazyRequest, "crazyUrl"))) {
				crazyAirList = (List<CrazyAirResponse>) FlightSearchUtility.jSonToObjectCrazyAir(clientService.sendGetCrazy(crazyRequest, "crazyUrl"));
			}  if (null != (clientService.sendGetTough(toughJetRequest, "toughUrl"))) {
				toughJetList = (List<ToughJetResponse>) FlightSearchUtility.jSonToObjectToughJet(clientService.sendGetTough(toughJetRequest, "toughUrl"));
			} else {
							flightData = populateDummyFlights(busyFlightsRequest);
				}
			flightData = convertListToJson(crazyAirList, toughJetList, busyFlightsRequest);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flightData = populateDummyFlights(busyFlightsRequest);
			e.printStackTrace();
		}

		return flightData;
	}
	

	private String populateDummyFlights(BusyFlightsRequest busyFlightsRequest){
		String flightsData = null ;
		List<CrazyAirResponse> crazyFlightList = (List<CrazyAirResponse>) FlightSearchUtility.jSonToObjectCrazyAir(clientService.crazyFlightJson);
		
		//JSON response being converted into List
		List<ToughJetResponse> toughJetFlightList = (List<ToughJetResponse>) FlightSearchUtility.jSonToObjectToughJet(clientService.toughFlightJson);
		flightsData = convertListToJson(crazyFlightList, toughJetFlightList, busyFlightsRequest);		
		
		return flightsData;
	}

	
	private  String convertListToJson(List<CrazyAirResponse> crazyAirList, List<ToughJetResponse> toughJetList, BusyFlightsRequest busyFlightsRequest) {
		List<Object> allFlightsList = new ArrayList<Object>();
		List<BusyFlightsResponse> reponseList = new ArrayList<BusyFlightsResponse>();
		String flightsData = null;
		// merging two list 
		allFlightsList.addAll((Collection<? extends Object>) crazyAirList);
        allFlightsList.addAll((Collection<? extends Object>) toughJetList);	
	
        reponseList = createBusyFlightsResponse(allFlightsList, busyFlightsRequest);
		for(BusyFlightsResponse obj:reponseList)
			System.out.println(obj);
		
		try {
	        	ObjectMapper mapper = new ObjectMapper();
	        	mapper.writeValue(System.out, allFlightsList);
	        	flightsData =	mapper.writeValueAsString(allFlightsList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	      
			return flightsData;
		
	}
	

	private List<BusyFlightsResponse> createBusyFlightsResponse(List<Object> allFlightsList, BusyFlightsRequest busyFlightsRequest) {
		BusyFlightsResponse busyFlightsResponse = null;
		List<BusyFlightsResponse> responseList = new ArrayList<BusyFlightsResponse>();
		for (Object object : allFlightsList) {
			if (object instanceof CrazyAirResponse){
				busyFlightsResponse = new BusyFlightsResponse();
				busyFlightsResponse.setAirline(((CrazyAirResponse) object).getAirline());
				busyFlightsResponse.setSupplier(CRAZYAIR);
				busyFlightsResponse.setFare(((CrazyAirResponse) object).getPrice());
				busyFlightsResponse.setDepartureAirportCode(((CrazyAirResponse) object).getDepartureAirportCode());
				busyFlightsResponse.setDestinationAirportCode(((CrazyAirResponse) object).getDestinationAirportCode());
				busyFlightsResponse.setDepartureDate(object.getDepartureDate());
				busyFlightsResponse.setArrivalDate(object.getArrivalDate());
				
				responseList.add(busyFlightsResponse);
				
			}else if(object instanceof ToughJetResponse){
				busyFlightsResponse = new BusyFlightsResponse();
				busyFlightsResponse.setAirline(((ToughJetResponse) object).getCarrier());
				busyFlightsResponse.setSupplier(TOUGHAIR);
				busyFlightsResponse.setFare((((ToughJetResponse) object).getBasePrice() + ((ToughJetResponse) object).getTax()) - 
						(((ToughJetResponse) object).getDiscount()/100)* (((ToughJetResponse) object).getBasePrice()));
				busyFlightsResponse.setDepartureAirportCode(((ToughJetResponse) object).getDepartureAirportName());
				busyFlightsResponse.setDestinationAirportCode(((ToughJetResponse) object).getArrivalAirportName());
				busyFlightsResponse.setDepartureDate(object.getDepartureDate());
				busyFlightsResponse.setArrivalDate(object.getOutboundDateTime());
				
				responseList.add(busyFlightsResponse);
			}
		}
		Collections.sort(responseList);
		return responseList;
	}
	
			
}
