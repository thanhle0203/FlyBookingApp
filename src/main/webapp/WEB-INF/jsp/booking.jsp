<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Flight Booking</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body class="bg-light">
<jsp:include page="header.jsp" />

<div class="container my-5">
    <h2 class="mb-5">Flight Booking</h2>

    <!-- Display selected flight details -->
    <div class="mb-4 bg-white p-4 shadow">
        <h4>Selected Flight:</h4>
        
            <strong>Flight Number:</strong> ${flight.flightNum} <br>
            <strong>Departure City:</strong> ${flight.departureCity} <br>
            <strong>Arrival City:</strong> ${flight.arrivalCity} <br>
            <strong>Operating Airlines:</strong> ${flight.operatingAirlines.airlinesName} <br>
            <strong>Departure Date:</strong> ${flight.departureDate} <br>
            <strong>Departure Time:</strong> ${flight.departureTime} <br>
            <strong>Ticket Price:</strong> ${flight.ticketPrice} <br>
        
    </div>

    <!-- Booking Form -->
    <form action="/completeBooking/${flight.flightId}/${numOfPassengers}" method="post" class="bg-white p-4 shadow">

        <c:forEach var="index" begin="1" end="${numOfPassengers}">
            <h5>Passenger ${index}</h5>
            <div class="form-group">
                <label for="fname">First Name:</label>
                <input type="text" name="fname" id="fname" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="lname">Last Name:</label>
                <input type="text" name="lname" id="lname" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" name="phone" id="phone" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="gender${index}">Gender:</label>
                <select name="gender${index}" id="gender${index}" class="form-control" required>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
        
            <div class="form-group">
                <label for="dateOfBirth${index}">Date of Birth:</label>
                <input type="date" name="dateOfBirth${index}" id="dateOfBirth${index}" class="form-control" required>
            </div>
        
            <div class="form-group">
                <label for="idType${index}">Identification Type:</label>
                <select name="idType${index}" id="idType${index}" class="form-control" required onchange="displayFields(this.id)">
                    <option value="REALID">Real ID</option>
                    <option value="DRIVERS_LICENSE">Driver's License</option>
                    <option value="PASSPORT">Passport</option>
                    <option value="SOCIAL_SECURITY_NUM">Social Security Number</option>
                </select>
            </div>

            <!--Related fields for REALID -->
            <div class="form-group related-fields" id="REALID${index}" style="display: none;">
                <label for="realIdNumber${index}">Real ID Number:</label>
                <input type="text" name="realIdNumber${index}" id="realIdNumber${index}" class="form-control">
            </div>

            <!--Related fields for DRIVERS_LICENSE -->
            <div class="form-group related-fields" id="DRIVERS_LICENSE${index}" style="display: none;">
                <label for="driversLicenseNumber${index}">Driver's License Number:</label>
                <input type="text" name="driversLicenseNumber${index}" id="realIdNumber${index}" class="form-control">
            </div>

            <!-- Fields for Passport-->
            <div class="related-fields" id="PASSPORT${index}" style="">
                <div>
                    <label for=""></label>
                    <input type="text">
                </div>
            </div>
            <hr>
        </c:forEach>

        <input type="submit" value="Confirm Booking" class="btn btn-primary">
    </form>
</div>

<jsp:include page="footer.jsp" />

<!-- Bootstrap JS, Popper.js, and jQuery
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->

<!-- Display Fields function-->
<script>
function displayFields(selectId) {
    let selectedValue = $("#" + selectId).val();
    let index = selectId.replace("idType", ""); // Extract index from the id

    // Hide all related fields first
    $(".related-fields").hile();

    // Show only the fields related to the selected value
    $("#" + selectedValue + index).show();
}
</script>
</body>
</html>
