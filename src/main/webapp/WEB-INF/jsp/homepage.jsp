<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Flight Search</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container my-5">
    <h2 class="mb-5">Flight Search</h2>

    <!-- Flight Search Form -->
    <form action="/searchFlights" method="get" class="bg-white p-4 shadow">
        <div class="form-group">
            <label for="departureCity">Departure City:</label>
            <input type="text" name="departureCity" id="departureCity" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="arrivalCity">Arrival City:</label>
            <input type="text" name="arrivalCity" id="arrivalCity" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="departureDate">Departure Date:</label>
            <input type="date" name="departureDate" id="departureDate" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="numOfPassengers">Number of Passengers:</label>
            <input type="number" name="numOfPassengers" id="numOfPassengers" class="form-control" min="1" value="1" required>
        </div>

        <input type="submit" value="Search" class="btn btn-primary">
    </form>

    <!-- Displaying the results -->
    <div id="results" class="mt-5">
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Departure City</th>
                    <th>Arrival City</th>
                    <th>Operating Airlines</th>
                    <th>Departure Date</th>
                    <th>Departure Time</th>
                    <th>Ticket Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Iterate over flights and populate the table rows -->
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
                            <a href="#" class="btn btn-primary">Select</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Display a message if no flights are found -->
        <c:if test="${empty flights}">
            <div class="alert alert-warning">
                No flights found for the given criteria.
            </div>
        </c:if>
    </div>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
