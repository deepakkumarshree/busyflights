<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<html lang="en">
<body>  <form:form method="post" action="search" commandName="model"> 
	<div>
		<div>
			<h1>Shree Partners Solutions</h1>
			<h2>Hello ${message}</h2>
		 
			 <table>
			 <tr><td>Origin</td><td><form:input path="origin" maxlength="3" value="LHR"/></td></tr>
			 <tr><td>Destination</td><td><form:input path="destination" maxlength="3" value="SHR"/></td></tr>
			 <tr><td>Departure Time</td><td><form:input path="departureDate" value="22-07-2017"/></td></tr>
			 <tr><td>Return Time</td><td><form:input path="returnDate" value="22-08-2017"/></td></tr>
			 <tr><td>Passenger Count</td><td><form:input path="numberOfPassengers" value="4"/></td></tr>
			 </table>
			
			<form:button type="submit" value="SEARCH" style="width:200px;height:25px;">SEARCH</form:button>
		</div>
	</div>
	</form:form>
</body>
</html>
