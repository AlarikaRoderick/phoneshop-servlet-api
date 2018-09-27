<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/common/header.jsp"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <title>Product page</title>
</head>
<body>
<form method="post" action="<c:url value="/products"/>/${product.id} ">
    <c:if test="${not empty addedQuantity}">
        Added ${addedQuantity} successfully
    </c:if>
<table bgcolor="#faebd7" border="2px" cellpadding="15px">
    <thead>
    <tr>
        <td>ID</td>
        <td>${product.id}</td>
    </tr>
    <tr>
        <td>Code</td>
        <td>${product.code}</td>
    </tr>
    <tr>
        <td>Description</td>
        <td>${product.description}</td>
    </tr>
    <tr>
        <td>Price</td>
        <td>${product.price} ${product.currency}</td>
    </tr>
    <tr>
        <td>Stock</td>
        <td>${product.stock}</td>
    </tr>
    <tr>
        <td>
            <input type="text" name="quantity" id="quantity" style="text-align: right"
            value="${empty param.quantity ? 1 : param.quantity}"><br>
            <c:if test="${error}">
                <label for="quantity" style="color: red; display: block">
                    ${errorMessage}
                </label>
            </c:if>
        </td>
        <td>
            <input type="submit" value="Add to cart">
        </td>
    </tr>
    </thead>
</table>
</form>
</body>
</html>
<jsp:include page="/WEB-INF/common/footer.jsp"/>