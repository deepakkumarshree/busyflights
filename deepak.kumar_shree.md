


Guide Line: 
Step 1: Run this application on http://localhost:8080
Step 2: UI Displayed
Step 3: Put the Request Form Value and click on the search button for the Response



 Work Flow :
   --> Controller --> Form Appear --->Form Submit
   --> BusyFlightRequest object Pass to Service to Invoke the API (CrazyAir and oughJet Both) 
   --> Result of the Both APIs Merge Together and Convert to BusyFlightRespone List
   --> Resultant List is sorted --> List Converted to JSON and Send in JSP Response 

JSTL dependency added to POM.xml
 
   
  
                        