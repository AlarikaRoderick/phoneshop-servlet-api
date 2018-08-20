<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<p>
  Hello from product list!
</p>
<table>
  <thead>
    <tr>
      <td>Code</td>
      <td>Description</td>
      <td>Price</td>
      <td>Stock</td>
      <td>ID</td>
    </tr>
  </thead>
  <c:forEach var="product" items="${products}">
    <tr>
      <%--<td>
        <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
      </td>--%>
      <td>${product.code}</td>
      <td>${product.description}</td>
      <td>${product.price} $</td>
      <td>${product.stock}</td>
      <td>${product.id}</td>
    </tr>
  </c:forEach>
</table>