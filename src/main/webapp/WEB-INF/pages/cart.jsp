
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cart" type="com.es.phoneshop.cart.Cart" scope="request"/>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<html>
<head>
    <title>Cart page</title>
</head>
<body>
<c:if test="${not empty param.success}">
    Updated successfully
</c:if>
<form method="post">
    <table bgcolor="#faebd7" border="2px">
        <thead>
        <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
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
                    <input name="quantity" id="quantity${status.index}" value="${quantities[status.index] != null ? quantities[status.index] : cartItem.quantity}">
                    <c:if test="${not empty errors[status.index]}">
                        <label for="quantity${status.index}" style="color: red; display: block">
                            ${errors[status.index]}
                        </label>
                    </c:if>
                </td>
                <td>
                    <button type="submit" value="${status.index}">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Update">
</form>
</body>
</html>
<jsp:include page="/WEB-INF/common/footer.jsp"/>
