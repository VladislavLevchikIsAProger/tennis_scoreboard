<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Player One</th>
        <th>Player Two</th>
        <th>Winner</th>
    </tr>
        <tr>
            <td>${match.playerOne}</td>
            <td>${match.playerTwo}</td>
            <td>${match.winnerName}</td>
        </tr>
</table>
</body>
</html>
