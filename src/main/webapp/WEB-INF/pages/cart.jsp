
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<html>
<head>
    <title>Cart page</title>
</head>
<body>
<table bgcolor="#faebd7" border="2px">
    <thead>
    <tr>
        <td>ID</td>
        <td>Code</td>
        <td>Description</td>
        <td>Price</td>
        <td>Quantity</td>
    </tr>
    </thead>
    <c:forEach var="cartItem" items="${cart.cartItems}">
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
</body>
</html>
<jsp:include page="/WEB-INF/common/footer.jsp"/>
