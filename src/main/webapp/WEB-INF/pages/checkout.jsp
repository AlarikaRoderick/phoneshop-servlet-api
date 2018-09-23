<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cart" type="com.es.phoneshop.cart.Cart" scope="request"/>
<jsp:include page="/WEB-INF/common/header.jsp"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <table bgcolor="#faebd7" border="2px">
        <thead>
            <tr>
                <th>ID</th>
                <th>Code</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="status">
            <tr>
                <td>${cartItem.product.id}</td>
                <td>
                <a href="${pageContext.servletContext.contextPath}/products/${cartItem.product.id}">${cartItem.product.code}</a>
                </td>
                <td>${cartItem.product.description}</td>
                <td>${cartItem.product.price} ${cartItem.product.currency}</td>
                <td>${cartItem.quantity}</td>
            </tr>
        </c:forEach>
    </table>
<form method="post">
    <p>
        <label for="name">Name</label>
        <input name="name" id="name">
    </p>
    <p>
        <label for="address">Address</label>
        <input name="address" id="address">
    </p>
    <p>
        <label for="phone">Phone</label>
        <input name="phone" id="phone">
    </p>
    <button>Place order</button>
</form>
</body>
</html>
