<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>$Title$</title>
</head>
<body>

<form id="upload" action="upload/" enctype="multipart/form-data" method="POST">
    <br>
    <p align="center">
        Choose xml file: <INPUT type="file" name="content">
    </p>
    <br>
    <p align="center">
        Choose the parser:
        <select name="parser" form="upload">
            <option>DOM</option>
            <option>SAX</option>
            <option>StAX</option>
        </select>
    </p>
    <br>
    <p align="center">
        <INPUT type="submit" value="Upload File">
    </p>
</form>

</body>
</html>
