
<jsp:include page="/WEB-INF/common/header.jsp"/>

<html>
<head>
    <title>Product page</title>
</head>
<body>

<table bgcolor="#faebd7" border="2px">
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
    </thead>
</table>
</body>
</html>
<jsp:include page="/WEB-INF/common/footer.jsp"/>