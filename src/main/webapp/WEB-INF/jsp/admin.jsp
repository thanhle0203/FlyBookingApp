<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Page</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="header.jsp" />

<div class="container mt-5">
    <h2>Admin Page</h2>
    <!-- Tabs Navigation -->
    <ul class="nav nav-tabs" id="adminTabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="reservation-tab" data-toggle="tab" href="#reservation" role="tab" aria-controls="reservation" aria-selected="true">Reservation</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="flight-tab" data-toggle="tab" href="#flight" role="tab" aria-controls="flight" aria-selected="false">Flight</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="airport-tab" data-toggle="tab" href="#airport" role="tab" aria-controls="airport" aria-selected="false">Airport</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="passenger-tab" data-toggle="tab" href="#passenger" role="tab" aria-controls="passenger" aria-selected="false">Passenger</a>
        </li>
    </ul>

    <!-- Tabs Content -->
    <div class="tab-content" id="adminTabsContent">
        <div class="tab-pane fade show active" id="reservation" role="tabpanel" aria-labelledby="reservation-tab">
            <!-- Reservation Management Content -->
        </div>
        <div class="tab-pane fade" id="flight" role="tabpanel" aria-labelledby="flight-tab">
            <!-- Flight Management Content -->
        </div>
        <div class="tab-pane fade" id="airport" role="tabpanel" aria-labelledby="airport-tab">
            <!-- Airport Management Content -->
        </div>
        <div class="tab-pane fade" id="passenger" role="tabpanel" aria-labelledby="passenger-tab">
            <!-- Passenger Management Content -->
        </div>
    </div>
</div>

<!-- Bootstrap JavaScript and dependencies -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<jsp:include page="footer.jsp" />

</body>
</html>
