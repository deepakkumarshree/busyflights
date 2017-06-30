package com.travix.medusa.busyflights;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.service.BusyFlightService;

@Controller
public class IndexController {
	@Autowired
	BusyFlightService busyFlightSearch;
	@RequestMapping("/")
	public ModelAndView home(Map<String, Object> model) {
		
		ModelAndView modelAndViewnew =new ModelAndView("index","model",new BusyFlightsRequest());
		modelAndViewnew.addObject("message", "Welcome to BusyFlight Search Solution !!");
		return modelAndViewnew;
	}
	
	@RequestMapping("/search")
	public ResponseEntity<?> search(@ModelAttribute("model") BusyFlightsRequest busyFlightsRequest) {
	
		String searchResData = null;
		if(((busyFlightsRequest.getNumberOfPassengers())==0) || ((busyFlightsRequest.getNumberOfPassengers())>4)) {
			return new ResponseEntity("Invalid No of Passengers ,use minimum 1 and maximum 4 passenger", HttpStatus.FORBIDDEN);
		}
		else {
			searchResData = busyFlightSearch.getAllFlights(busyFlightsRequest);
				return new ResponseEntity<>(searchResData, HttpStatus.OK);
		}
		
		
	}

}