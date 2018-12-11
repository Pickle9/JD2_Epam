<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Error</title>
</head>
<body>

<p align="center">
    <b>Oouuppss... Something was wrong. Please, try again.</b>
</p>

<p align="center">
    <b>${pageContext.errorData.throwable.localizedMessage}</b>
</p>

</body>
</html>
