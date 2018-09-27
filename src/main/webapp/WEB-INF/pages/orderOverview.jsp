<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Overview</title>
</head>
<body>
<h3>Your Order</h3>
<table bgcolor="#faebd7" border="2px">
    <thead>
    <tr>
        <td>Name</td>
        <td>${order.name}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${order.address}</td>
    </tr>
    <tr>
        <td>Phone</td>
        <td>${order.phone}</td>
    </tr>
    <tr>
        <td>Order Sum</td>
        <td>${order.orderSum}</td>
    </tr>
    </thead>
</table>
</body>
</html>
