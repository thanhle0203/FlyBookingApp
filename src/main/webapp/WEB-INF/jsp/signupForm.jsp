<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<jsp:include page="header.jsp" />

<h2>Sign Up Form</h2>

<form:form action="/signup" modelAttribute="signupForm" method="POST">

    <!-- User Fields -->
    <div>
        <label for="username">Username:</label>
        <form:input path="username" id="username" required="true"/>
    </div>
    <div>
        <label for="password">Password:</label>
        <form:password path="password" id="password" required="true"/>
    </div>

    <!-- Passenger Fields -->
    <div>
        <label for="fname">First Name:</label>
        <form:input path="fname" id="fname" />
    </div>
    <div>
        <label for="lname">Last Name:</label>
        <form:input path="lname" id="lname" />
    </div>
    <div>
        <label for="email">Email:</label>
        <form:input path="email" id="email" />
    </div>
    <div>
        <label for="phone">Phone:</label>
        <form:input path="phone" id="phone" />
    </div>

    </div>

    <!-- Role Selection -->
    <div>
        <label for="role">Role:</label>
        <form:select path="role" id="role">
            <form:option value="" label="Select a role" />
            <form:option value="USER" label="User" />
            <form:option value="ADMIN" label="Admin" />
        </form:select>
    </div>

    <!-- Submit Button -->
    <div>
        <button type="submit">Sign Up</button>
    </div>

</form:form>

</body>
<jsp:include page="footer.jsp" />

</html>
