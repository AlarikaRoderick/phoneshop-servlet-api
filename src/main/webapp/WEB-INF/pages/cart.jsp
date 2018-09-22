
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cart" type="com.es.phoneshop.cart.Cart" scope="request"/>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<html>
<head>
    <title>Cart page</title>
</head>
<body>
<form method="post">
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
        <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="status">
            <tr>
                <td>${cartItem.product.id}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/products/${cartItem.product.id}">${cartItem.product.code}</a>
                </td>
                <td>${cartItem.product.description}</td>
                <td>${cartItem.product.price} ${cartItem.product.currency}</td>
                <td>
                    <input type="hidden" name="productId" value="${cartItem.product.id}">
                    <input name="quantity" value="${cartItem.quantity}">
                    ${errors[status.index]}
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Update">
</form>
</body>
</html>
<jsp:include page="/WEB-INF/common/footer.jsp"/>
