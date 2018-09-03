<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<html>
<head>
  <title>Product list page</title>
</head>
<body>
<div>
<p>
  Hello from product list!
</p>
<table bgcolor="#faebd7" border="2px">
  <thead>
    <tr>
      <td>ID</td>
      <td>Code</td>
      <td>Description</td>
      <td>Price</td>
      <td>Stock</td>
    </tr>
  </thead>
  <c:forEach var="product" items="${products}">
    <tr>
      <td>${product.id}</td>
        <td>
          <a href="${pageContext.servletContext.contextPath}/products/${product.id}">${product.code}</a>
        </td>
      <td>${product.description}</td>
      <td>${product.price} ${product.currency}</td>
      <td>${product.stock}</td>
    </tr>
  </c:forEach>
</table>
</div>
</body>
</html>
<jsp:include page="/WEB-INF/common/footer.jsp"/>