<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="banks" scope="request" type="java.util.List"/>
<jsp:useBean id="parserType" scope="request" type="java.lang.String"/>

<html>
<head>
    <title>Result</title>
</head>
<body>
<p align="center">${parserType}</p>
<table class="banks-table" align="center">
    <tr>
        <th>Name</th>
        <th>Country</th>
        <th>Type</th>
        <th>Depositor</th>
        <th>Account_Id</th>
        <th>Amount</th>
        <th>Profitability</th>
        <th>Time_Constraints</th>
    </tr>

    <c:forEach items="${banks}" var="bank">
        <tr>
            <td align="center">${bank.getName()}</td>
            <td align="center">${bank.getCountry()}</td>
            <td align="center">${bank.getType().getValue()}</td>
            <td align="center">${bank.getDepositor()}</td>
            <td align="center">${bank.getAccountId()}</td>
            <td align="center">${bank.getAmount()}$</td>
            <td align="center">${bank.getProfitability()}%</td>
            <td align="center">${bank.getStringTimeConstraints()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
