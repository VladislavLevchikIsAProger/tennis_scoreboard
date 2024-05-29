<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New match</title>
</head>
<body>
    <div>
        <form method="post" action="new-match">
            <label for="playerOne">Player one</label>
            <input type="text" id="playerOne" name="playerOne" pattern="[A-Za-z]\. [A-Za-z]+" required title="Enter a name in the format n. surname "><br><br>
            <label for="playerTwo">Player two</label>
            <input type="text" id="playerTwo" name="playerTwo" pattern="[A-Za-z]\. [A-Za-z]+" required title="Enter a name in the format n. surname "><br><br>
            <input type="submit" value="Start">
        </form>
    </div>
</body>
</html>
