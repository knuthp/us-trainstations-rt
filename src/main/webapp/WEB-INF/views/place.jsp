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
<table>
	<c:forEach items="${placeList}" var="place">
    	<h3>Place ID: <c:out value="${place.id}"/></h3>
	</c:forEach>
</table>
</body>
</html>
