# fossee-2020

# Task-1

## Problem Statement
Create a Web Application that allows a user to upload a CSV file. The  CSV file should contain the following fields : [ Name, Email, Phone Number]. 

1. Store the data received from the CSV file into a MYSQL database. Read and render/display the stored data on a webpage.
2. Technology: JSP, Servlet and MySQL.

## Overview
csvWeb is a Java web application built for the JSP community that has two primary goals:
1. Allow user to upload CSV file and further store the same into MYSQL database. 
2. Read and display the data stored on webpage.

## Technology tools/components used
- JAVA Servlet
- JSP
- MYSQL
- HTML/CSS

## How to Run csvWeb

1. Clone the repository from GitHub:

		$ git clone git://github.com/seemantshekhar43/fossee-task.git

2. Create table to store data:

		mysql> create table data(name  text null, email text null, phone text null);
    
3. Open downloaded project in IntelliJ/ Eclipse.

4. Make the following changes in upload_page.jsp and display.jsp:
  
      - Replace YOUR_DB_NAME with name of your database
      - Replace USERNAME with username of MYSQL database
      - Replace PASSWORD with passowrd of MYSQL database

5. Build the project and Run on Tomcat server.

6. I have included a demo csv file for testing named "demoData.csv".

    
    
    


