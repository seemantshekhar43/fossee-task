
<%@ page language="java" %>
<HTML>
<HEAD><TITLE>CSV UPLOAD</TITLE>
<link rel="stylesheet" href="./public/style.css">
<script src = "./public/jquery-3.4.1.min.js"></script>
<script src = "./public/index.js"></script>
</HEAD> 


<BODY>
<h2 class = "center_align" style="margin-top: 50px;">Upload CSV File</h2>
<form ENCTYPE="multipart/form-data" ACTION=
"upload_page.jsp" METHOD=POST id = "myForm">
		
		<label class="file">
				<span id = "file_name">Drop a file or click to select one</span>
				<INPUT NAME="file" TYPE="file"  required>
				<!-- use multiple, eve	n if it’s not allowed, to be able to show an info text -->
		</label>
		<span id = "file_name"></span>
		<button type="submit">Upload</button>
		<button type="button" class="reset-btn">Reset</button>
		
		
</form>
<a href = "display.jsp"><button style="display: block; margin: 0 auto;" >Display Data</button></a>


</BODY>
</HTML>
