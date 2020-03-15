<HTML>
<HEAD><TITLE>DATA</TITLE>

</HEAD>
<BODY>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>


<%
    /**
     *               Database Connection Details
     *
     * Replace YOUR_DB_NAME with name of your database
     * Replace USERNAME with username of MYSQL database
     * Replace PASSWORD with password of MYSQL database
     **/

    String driverName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String dbName = "blog_db";
    String userId = "root";
    String password = "ubuntu@43";

    try {
        Class.forName(driverName);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Details from MySQL Database</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
    <tr>

    </tr>
    <tr bgcolor="#A52A2A">
        <td><b>name</b></td>
        <td><b>email</b></td>
        <td><b>phone</b></td>
    </tr>
    <%
        try {
            /**
             * Establishing connection with Database
             */
            connection = DriverManager.getConnection(connectionUrl + dbName, userId, password);

            /**
             * MYSQL Query to SELECT ALL from data table
             */
            statement = connection.createStatement();
            String sql = "SELECT * FROM data";

            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
    %>
    <tr bgcolor="#DEB887">

        <td><%=resultSet.getString("name") %>
        </td>
        <td><%=resultSet.getString("email") %>
        </td>
        <td><%=resultSet.getString("phone") %>
        </td>
    </tr>

    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
</BODY>
</HTML>
