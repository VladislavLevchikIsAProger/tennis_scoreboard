<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:if test="${not empty match}">
        <h1>Match Score</h1>
        <table>
            <thead>
            <tr>
                <th>Player</th>
                <th>Score</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${match.playerOne.name}</td>
                <td>${match.playerOne.score}</td>
            </tr>
            <tr>
                <td>${match.playerTwo.name}</td>
                <td>${match.playerTwo.score}</td>
            </tr>
            </tbody>
        </table>

        <form action="${pageContext.request.contextPath}/match-score" method="post">
            <input type="hidden" name="uuid" value="${match.id}" />
            <button type="submit" name="playerId" value="${match.playerOne.name}">Player One wins point</button>
            <button type="submit" name="playerId" value="${match.playerTwo.name}">Player Two wins point</button>
        </form>
    </c:if>

</body>
</html>
