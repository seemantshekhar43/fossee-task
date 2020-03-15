<%@ page import="java.io.*,java.sql.*" %>
<HTML>
<HEAD><TITLE>UPLOADED SUCCESSFULLY</TITLE>

</HEAD>
<BODY>

<%--------------------Getting the file uploaded by user----------------------%>
<%
    String contentType = request.getContentType();
    if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
        DataInputStream in = new DataInputStream(request.getInputStream());
        int formDataLength = request.getContentLength();
        byte dataBytes[] = new byte[formDataLength];
        int byteRead = 0;
        int totalBytesRead = 0;

        while (totalBytesRead < formDataLength) {
            byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
            totalBytesRead += byteRead;
        }


        /**
         *      saving the file we got above
         */
        String file = new String(dataBytes);
        String saveFile = file.substring(file.indexOf("filename=\"") + 10);
        System.out.println("saveFile=" + saveFile);
        saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
        System.out.println("saveFile" + saveFile);
        saveFile = file.substring(file.indexOf("filename=\"") + 10);
        saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
        saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
        int lastIndex = contentType.lastIndexOf("=");
        String boundary = contentType.substring(lastIndex + 1, contentType.length());
        int pos;

        pos = file.indexOf("filename=\"");
        pos = file.indexOf("\n", pos) + 1;
        pos = file.indexOf("\n", pos) + 1;
        pos = file.indexOf("\n", pos) + 1;
        int boundaryLocation = file.indexOf(boundary, pos) - 4;
        int startPos = ((file.substring(0, pos)).getBytes()).length;
        int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;

        FileOutputStream fileOut = new FileOutputStream(saveFile);
        fileOut.write(dataBytes, startPos, (endPos - startPos));
        Connection con = null;
        PreparedStatement statement = null;
        String line = null;

        int count = 0;
        int batchSize = 20;
        try {
            /**
             *              Creating Database Connection
             *
             * Replace YOUR_DB_NAME with name of your database
             * Replace USERNAME with username of MYSQL database
             * Replace PASSWORD with password of MYSQL database
             */

            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://localhost:3306/blog_db";
            con = DriverManager.getConnection
                    (connectionURL, "root", "ubuntu@43");
            String sql = "INSERT INTO data(name,email,phone) VALUES(?,?,?)"; // SQL Query to insert a tupple in a table
            statement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            BufferedReader input = new BufferedReader(new FileReader(saveFile));

            input.readLine(); // skipping the header line
            while ((line = input.readLine()) != null) {

                String[] data = line.split(",");
                String name = data[0];
                String email = data[1];
                String phone_no = data[2];

                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, phone_no);
                System.out.println(statement);
                statement.addBatch();

                if (count % batchSize == 0) {
                    int[] res = statement.executeBatch();
                    con.commit();
                }

            }
            int[] res = statement.executeBatch();
            con.commit();
            con.close();
            input.close();
        } catch (Exception e) {
        }
    }
%>
<B>File Uploaded Successfully!!</B>

<a href="display.jsp">
    <button style="display: block; margin: 0 auto;	color: #eee;
		background-color: #666;
		padding: 1rem 2rem;
		border: 0;
		outline: none;">Display Data
    </button>
</a>
</BODY>
</HTML>
