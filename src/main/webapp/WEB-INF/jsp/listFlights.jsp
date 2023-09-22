<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Flights</title>
    <!-- Including Bootstrap CSS for styling (assuming you have an internet connection) -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>Available Flights</h2>

    <!-- Display a message if no flights are available -->
    <c:if test="${empty flights}">
        <p>No flights available for the selected criteria. Please try a different search.</p>
    </c:if>
    
    <!-- Display the flights in a table format if there are any -->
    <c:if test="${not empty flights}">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Departure City</th>
                    <th>Arrival City</th>
                    <th>Departure Date</th>
                    <th>Departure Time</th>
                    <!-- <th>Arrival Time</th> -->
                    <th>Ticket Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="flight" items="${flights}">
                    <tr>
                        <td>${flight.flightNum}</td>
                        <td>${flight.departureCity}</td>
                        <td>${flight.arrivalCity}</td>
                        <td>${flight.operatingAirlines.airlinesName}</td>
                        <td>${flight.departureDate}</td>
                        <td>${flight.departureTime}</td>
                        <td>${flight.ticketPrice}</td>
                        <td>
                            <!-- Booking Link -->
                            <a href="/bookFlight/${flight.flightId}/${numOfPassengers}" class="btn btn-primary">Book</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<!-- Optionally include Bootstrap JS and jQuery if needed for additional functionality -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
