<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Booking Details</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body class="bg-light">
<jsp:include page="header.jsp" />

<div class="container my-5">
    <h2 class="mb-5">Booking Details</h2>

    <!-- Display reservation details -->
    <div class="mb-4 bg-white p-4 shadow">
        <h4>Reservation Details:</h4>


         
        <strong>Reservation ID:</strong> ${reservation.reservationId} <br>
        <strong>Flight Number:</strong> ${reservation.flight.flightNum} <br>
        <strong>Departure City:</strong> ${reservation.flight.departureCity} <br>
        <strong>Arrival City:</strong> ${reservation.flight.arrivalCity} <br>
        <strong>Operating Airlines:</strong> ${reservation.flight.operatingAirlines.airlinesName} <br>
        <strong>Departure Date:</strong> ${reservation.flight.departureDate} <br>
        <strong>Departure Time:</strong> ${reservation.flight.departureTime} <br>
        <strong>Ticket Price:</strong> ${reservation.flight.ticketPrice} <br>
        
        <hr>
        
        <strong>Passenger(s):</strong>
        <c:if test="${not empty reservation.passengers}">
    <c:forEach var="passenger" items="${reservation.passengers}">
        <div>
            <strong>Name:</strong> ${passenger.fname} ${passenger.lname} <br>
            <strong>Email:</strong> ${passenger.email} <br>
            <strong>Phone:</strong> ${passenger.phone} <br>
            <!-- ... other passenger details ... -->
        </div>
        <hr>
    </c:forEach>
</c:if>

        
        
    </div>

    <a href="/" class="btn btn-primary">Back to Home</a>
</div>

<jsp:include page="footer.jsp" />

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
