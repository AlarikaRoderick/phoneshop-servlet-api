<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Helen_Roderick
  Date: 27.09.2018
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="product" type="com.es.phoneshop.model.Product" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="/compare">
<table bgcolor="#faebd7" border="2px" cellpadding="15px">
    <thead>
    <tr>
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
    </thead>
</table>
</form>
</body>
</html>
