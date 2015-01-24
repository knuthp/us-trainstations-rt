<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Norwegian Trainstation poller</title>
</head>
<body>
<h1>
	Train stations  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<P>  The number of places polled ${numberOfPlaces}. </P>
<form action="/reset" method="post">
	<input type="submit" value="Reset" />
</form>
<h2>Places</h2>
<table>
	<c:forEach items="${currentDepartures}" var="departures">
    	<h3>Place ID: <c:out value="${departures.key.id}"/></h3>
    	<table>
    		<tr>
    			<th>PublishedLineName</th>
    			<th>Destination</th>
    			<th>JourneyId</th>
    			<th>Monitored</th>
    			<th>ExpArrival</th>
    			<th>ExpDepart</th>
    			<th>AimedArrival</th>
    			<th>AimedDepart</th>
    			<th>Delay</th>
    		</tr>
	    	<c:forEach items="${departures.value.rtStopList}" var="rtStop">
	    		<tr>
	    			<td><c:out value="${rtStop.publishedLineName}"/></td>
	    			<td><c:out value="${rtStop.destinationName}"/></td>
	    			<td><c:out value="${rtStop.journeyId}"/></td>
	    			<td><c:out value="${rtStop.monitored}"/></td>
	    			<td><c:out value="${rtStop.expectedArrivalTime}"/></td>
	    			<td><c:out value="${rtStop.expectedDepartureTime}"/></td>
	    			<td><c:out value="${rtStop.aimedArrivalTime}"/></td>
	    			<td><c:out value="${rtStop.aimedDepartureTime}"/></td>
	    			<td><c:out value="${rtStop.delay}"/></td>
	    		</tr>
	    	</c:forEach>
    	</table>
	</c:forEach>
</table>
</body>
</html>
