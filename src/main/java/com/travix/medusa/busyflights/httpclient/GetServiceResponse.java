package com.travix.medusa.busyflights.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;

@Service("GetServiceResponse")
public class GetServiceResponse {

	public static final Logger logger = LoggerFactory.getLogger(GetServiceResponse.class);

	
	public String sendGetCrazy(CrazyAirRequest crazyReq, String crazyUrl) {
		String crazyHTTP = "http"; // https
		String crazyPort = "80";
		URL objUrl;
		BufferedReader in;
		StringBuffer response = null;
		try {
			objUrl = new URL(crazyUrl);
			InetSocketAddress proxyinet = new InetSocketAddress(crazyHTTP, Integer.parseInt(crazyPort));
			Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyinet);
			HttpURLConnection con = (HttpURLConnection) objUrl.openConnection(proxy);
			con.setRequestMethod("GET");
			int responsecode = con.getResponseCode();
			logger.info("Fetching CrzyAirResponse for CrazyAirRequest{} ::", crazyReq);
			logger.info("Fetching CrzyAirResponse Response code ::", responsecode);

			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		 if(null != response) {
			 return response.toString();
		 } else {
			 return crazyFlightJson;
		 }

	}

	public String sendGetTough(ToughJetRequest toughReq, String toughUrl) {
		String crazyHTTP = "http"; // https
		String crazyPort = "82";
		URL objUrl;
		BufferedReader in;
		StringBuffer response = null;
		try {

			objUrl = new URL(toughUrl);
			InetSocketAddress proxyinet = new InetSocketAddress(crazyHTTP, Integer.parseInt(crazyPort));
			Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyinet);
			HttpURLConnection con = (HttpURLConnection) objUrl.openConnection(proxy);
			con.setRequestMethod("GET");
			int responsecode = con.getResponseCode();
			logger.info("Fetching CrzyAirResponse for CrazyAirRequest{} ::", toughReq);
			logger.info("Fetching CrzyAirResponse Response code ::", responsecode);

			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if(null != response) {
			 return response.toString();
		 } else {
			 return toughFlightJson;
		 }

	}
	
	public String crazyFlightJson =  ""
            + "[{\"airline\": \"Jet Airways\",\"price\": \"15000\",\"cabinclass\": \"B\",\"departureAirportCode\": \"LHR\","
            + "\"destinationAirportCode\": \"AMS\",   \"departureDate\": \"2017-11-04 10:10\",\"arrivalDate\": \"2017-12-06 12:30\"}, "
            + "{\"airline\": \"Air Pegasus\",\"price\": \"74000\",      \"cabinclass\": \"E\",  \"departureAirportCode\": \"LHR\",  "
            + "\"destinationAirportCode\": \"AMS\",   \"departureDate\": \"2017-11-04 10:10\",    \"arrivalDate\": \"2017-12-11 12:30\"     },    "
            + "{\"airline\": \"Air India\",  \"price\": \"80000\",      \"cabinclass\": \"E\",  \"departureAirportCode\": \"LHR\",  "
            + "\"destinationAirportCode\": \"AMS\",   \"departureDate\": \"2017-11-08 10:10\",    \"arrivalDate\": \"2017-12-14 12:30\" } ]";;
	
	public String toughFlightJson =  ""
            + "[{\"carrier\": \"Deccan Shuttles\",\"basePrice\": \"25000\",\"tax\": \"1744\",\"discount\": \"3\",\"departureAirportName\": \"LHR\","
            + "\"arrivalAirportName\": \"AMS\", \"outboundDateTime\": \"2017-10-15 9:20\",\"inboundDateTime\": \"2017-12-13 10:50\"},      "
            + "{\"carrier\": \"Jet Konnect\",\"basePrice\": \"30000\",\"tax\": \"1867\",\"discount\": \"2\",      \"departureAirportName\": \"LHR\",  "
            + "\"arrivalAirportName\": \"AMS\", \"outboundDateTime\": \"2017-11-16 09:20\", \"inboundDateTime\": \"2017-12-12 10:50\" },    "
            + "{\"carrier\": \"SpiceJet Airlines\", \"basePrice\": \"84000\",\"tax\": \"9950\",\"discount\": \"4\",  \"departureAirportName\": \"LHR\",  "
            + "\"arrivalAirportName\": \"AMS\", \"outboundDateTime\": \"2017-11-09 09:20\", \"inboundDateTime\": \"2017-12-23 10:50\" }]";


}
