package com.travix.medusa.busyflights.utility;


import java.io.IOException;
import java.util.List;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

public class FlightSearchUtility {
	
    
    public static List<CrazyAirResponse> jSonToObjectCrazyAir(String responseJSON) {
		List<CrazyAirResponse> list = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			list = mapper.readValue(responseJSON, new TypeReference<List<CrazyAirResponse>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
    
    public static List<ToughJetResponse> jSonToObjectToughJet(String responseJSON) {
		List<ToughJetResponse> list = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			list = mapper.readValue(responseJSON, new TypeReference<List<ToughJetResponse>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
		
}
