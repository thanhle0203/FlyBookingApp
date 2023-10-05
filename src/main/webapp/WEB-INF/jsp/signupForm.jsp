<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Sign Up Form</h2>

    <form action="/signup" method="POST" class="form-horizontal" modelAttribute="signupForm">

        <!-- User Fields -->
        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Username:</label>
            <div class="col-sm-10">
                <input type="text" name="username" id="username" required="true" class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-10">
                <input type="password" name="password" id="password" required="true" class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-10">
                <input type="email" name="email" id="email" class="form-control"/>
            </div>
        </div>

        <!-- Role Selection -->
        <div class="form-group row">
            <label for="role" class="col-sm-2 col-form-label">Role:</label>
            <div class="col-sm-10">
                <select name="role" id="role" class="form-control">
                    <option value="">Select a role</option>
                    <option value="USER">User</option>
                    <option value="ADMIN">Admin</option>
                </select>
            </div>
        </div>

        <!-- CSRF Token -->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <!-- Submit Button -->
        <div class="form-group row">
            <div class="col-sm-10 offset-sm-2">
                <button type="submit" class="btn btn-primary">Sign Up</button>
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
