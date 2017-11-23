<%@page import="java.util.List"%>
<%@page import="edu.uwaterloo.ece658.entity.User"%>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Phototrest Example Signup User Page</title>
    </head>

    <body>
        <form method="POST" action="user">
            Name: <input type="text" name="name" />
            <input type="submit" value="Add" />
        </form>

        <hr><ol> <%
            @SuppressWarnings(  "unchecked")
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null) {
                for (User user : users) {%>
            <li> <%= user%> </li> <%
                    }
                }%>
        </ol><hr>
    </body>
</html>
