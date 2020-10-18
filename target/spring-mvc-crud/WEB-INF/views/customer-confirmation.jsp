<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer form</title>
</head>
<body>
The customer is confirmed: ${customer.firstName} ${customer.lastName}
<br><br>
Free passes: ${customer.freePasses}
<br><br>
Postal code ${customer.postalCode}
<br><br>
Course code ${customer.courseCode}
</body>
</html>
