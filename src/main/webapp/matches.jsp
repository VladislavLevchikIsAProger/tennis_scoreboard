<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Matches</title>
</head>

<body>

<h1>All Matches</h1>

<div>
    <form method="get" action="matches">
        <label for="filter_by_player_name">Player Name:</label>
        <input type="text" id="filter_by_player_name" name="filter_by_player_name"
               value="${param.filter_by_player_name}" pattern=".*\S.*" required
               title="Player name cannot be empty or just spaces.">
        <input type="submit" value="Filter">
    </form>

</div>

<div>
    <a href="matches">
        <button>Reset Filter</button>
    </a>
</div>

<table>
    <tr>
        <th>Player One</th>
        <th>Player Two</th>
        <th>Winner</th>
    </tr>
    <c:forEach var="match" items="${matches}">
        <tr>
            <td>${match.playerOne.name}</td>
            <td>${match.playerTwo.name}</td>
            <td>${match.winner.name}</td>
        </tr>
    </c:forEach>
</table>

<%-- Пагинация --%>
<div>
    <c:if test="${totalPages > 1}">
        <c:if test="${currentPage > 1}">
            <c:choose>
                <c:when test="${not empty param.filter_by_player_name}">
                    <a href="matches?page=${currentPage - 1}&filter_by_player_name=${param.filter_by_player_name}">Previous</a>
                </c:when>
                <c:otherwise>
                    <a href="matches?page=${currentPage - 1}">Previous</a>
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${empty param.filter_by_player_name}">
                            <a href="matches?page=${i}">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="matches?page=${i}&filter_by_player_name=${param.filter_by_player_name}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <c:choose>
                <c:when test="${not empty param.filter_by_player_name}">
                    <a href="matches?page=${currentPage + 1}&filter_by_player_name=${param.filter_by_player_name}">Next</a>
                </c:when>
                <c:otherwise>
                    <a href="matches?page=${currentPage + 1}">Next</a>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:if>
</div>

</body>

</html>
