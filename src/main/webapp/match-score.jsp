<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Match Score</h1>
    <table>
        <thead>
        <tr>
            <th>Player</th>
            <th>Sets</th>
            <th>Games</th>
            <th>Points</th>
        </tr>
        </thead>

        <tbody>
            <tr>
                <td>${match.playerOne.playerName}</td>
                <td>${match.playerOne.sets}</td>
                <td>${match.playerOne.games}</td>
                <td>
                    <c:set var="playerOnePoints" value="${match.playerOne.points}" />
                    <c:set var="playerTwoPoints" value="${match.playerTwo.points}" />

                    <c:choose>
                        <c:when test="${playerOnePoints >= 3 && playerTwoPoints >= 3}">
                            <%-- Оба игрока имеют по 40 очков --%>
                            <c:choose>
                                <c:when test="${match.playerOne.points > match.playerTwo.points}">
                                    >
                                </c:when>
                                <c:when test="${match.playerOne.points < match.playerTwo.points}">
                                    <
                                </c:when>
                                <c:otherwise>
                                    =
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <%-- Преобразование очков в теннисный формат --%>
                            <c:choose>
                                <c:when test="${playerOnePoints == 0}">
                                    0
                                </c:when>
                                <c:when test="${playerOnePoints == 1}">
                                    15
                                </c:when>
                                <c:when test="${playerOnePoints == 2}">
                                    30
                                </c:when>
                                <c:when test="${playerOnePoints == 3}">
                                    40
                                </c:when>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>${match.playerTwo.playerName}</td>
                <td>${match.playerTwo.sets}</td>
                <td>${match.playerTwo.games}</td>
                <td>
                    <c:set var="playerOnePoints" value="${match.playerOne.points}" />
                    <c:set var="playerTwoPoints" value="${match.playerTwo.points}" />

                    <c:choose>
                        <c:when test="${playerOnePoints >= 3 && playerTwoPoints >= 3}">
                            <%-- Оба игрока имеют по 40 очков --%>
                            <c:choose>
                                <c:when test="${match.playerOne.points < match.playerTwo.points}">
                                    >
                                </c:when>
                                <c:when test="${match.playerOne.points > match.playerTwo.points}">
                                    <
                                </c:when>
                                <c:otherwise>
                                    =
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <%-- Преобразование очков в теннисный формат --%>
                            <c:choose>
                                <c:when test="${playerTwoPoints == 0}">
                                    0
                                </c:when>
                                <c:when test="${playerTwoPoints == 1}">
                                    15
                                </c:when>
                                <c:when test="${playerTwoPoints == 2}">
                                    30
                                </c:when>
                                <c:when test="${playerTwoPoints == 3}">
                                    40
                                </c:when>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </tbody>
    </table>

    <form method="post" action="match-score?uuid=${uuid}">
        <input type="hidden" name="playerId" value="${match.playerOne.id}"/>
        <button type="submit">${match.playerOne.playerName} wins the point</button>
    </form>
    <form method="post" action="match-score?uuid=${uuid}">
        <input type="hidden" name="playerId" value="${match.playerTwo.id}"/>
        <button type="submit">${match.playerTwo.playerName} wins the point</button>
    </form>

</body>
</html>
