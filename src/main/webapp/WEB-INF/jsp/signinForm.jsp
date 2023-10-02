<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign In</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <style>
        html, body {
            height: 100%;
        }
        .container {
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        label {
            margin-bottom: 0.5rem; /* Added space below labels */
        }
        .form-horizontal {
            width: 33%; /* Set width to 1/3 */
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp" />

<div class="container">
    

    <form action="/signin" method="POST" class="form-horizontal" modelAttribute="signInForm">
        <h2 class="text-center">Sign In</h2>

        <!-- Error Message -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Email Field -->
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label mr-3">Email:</label>
            <div class="col-sm-9">
                <input type="email" name="email" id="email" required="true" class="form-control"/>
            </div>
        </div>

        <!-- Password Field -->
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label mr-3">Password:</label>
            <div class="col-sm-9">
                <input type="password" name="password" id="password" required="true" class="form-control"/>
            </div>
        </div>

        <!-- Submit Button -->
        <div class="form-group row">
            <div class="col-sm-10 text-center">
                <button type="submit" class="btn btn-primary text-center">Sign In</button>
            </div>
        </div>

    </form>
</div>

<!-- Bootstrap JavaScript and dependencies -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<jsp:include page="footer.jsp" />

</body>
</html>
