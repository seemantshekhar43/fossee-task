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
- JQUERY


## How to Run csvWeb

1. Clone the repository from GitHub:

		$ git clone git://github.com/seemantshekhar43/fossee-task.git

2. Create table to store data:

		mysql> create table data(name  text null, email text null, phone text null);
    
3. Open downloaded project in IntelliJ/ Eclipse.

4. Make the following changes in upload_page.jsp and display.jsp:
  
      - Replace YOUR_DB_NAME with name of your database
      - Replace USERNAME with username of MYSQL database
      - Replace PASSWORD with password of MYSQL database
      
      
     In upload_page.jsp
      
      ```java
       Class.forName("com.mysql.jdbc.Driver");
       String connectionURL = "jdbc:mysql://localhost:3306/YOUR_DB_NAME";
       con = DriverManager.getConnection
                 connectionURL, "USERNAME", "PASSWORD");
      ```  
      
      In display.jsp
      
      ```java
    String driverName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String dbName = "YOUR_DB_NAME";
    String userId = "USERNAME";
    String password = "PASSWORD";
      ```  
      

5. Build the project and Run on Tomcat server.

6. I have included a demo csv file for testing named "demoData.csv".


# Task-2

## Problem Statement
Create a Traveller blog application. An example format of the webpage has been shown below:

![Alt text](relative/path/to/img.jpg?raw=true "Title")

Do the following validations on the page:
1. Date: Form should not accept date greater than the current date.

2. Image: The image file must be JPEG/JPG/PNG format only and the size of the file should not exceed 5 MB.

3. Allow only 100 characters for the Name field.

- Display all blogs in descending order (based on the date of creation) in a page.

- Give all users View/Edit/Delete option for each blog. 

- Note: The image should be stored in a directory and the relative path of the image should be stored in the database.

- Technology: Spring boot, Hibernate, Mysql, Thymeleaf, BootStrap, CSS.

## Overview
Traveller's Blog is a Java web application built for the Spring community. This is blogging site having basic blogger features for travellers. 

## Features
- Create Account
- Login/Logout
- Add new Post (Can add image to the post too.)
- Display Post
- Edit Post
- Delete Post

## Technology tools/components used
- JAVA 
- Spring MVC
- Spring Boot
- JPA
- Hibernate
- Thymeleaf
- JQUERY
- Bootstrap
- MYSQL

## How to run Traveller's Blog

1. Clone the repository from GitHub:

		$ git clone git://github.com/seemantshekhar43/fossee-task.git
    
2. Open project in IntelliJ/Eclipse.

3. Make the following changes in application.properties:
  
      - Replace YOUR_DB_NAME with name of your database
      - Replace USERNAME with username of MYSQL database
      - Replace PASSWORD with password of MYSQL database
      
      application.properties
      
      ```java
    spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
    spring.datasource.url = jdbc:mysql://localhost:3306/YOUR_DB_NAME?characterEncoding=utf8
    spring.datasource.username = USERNAME
    spring.datasource.password = PASSWORD
      ```
      
      NOTE: Change the values for "spring.jpa.hibernate.ddl-auto"  
      
      1. If you are running for the first time.
      ```java
       spring.jpa.hibernate.ddl-auto = create 
      ```
      2. If you are running for the 2nd time or else.
      ```java
       spring.jpa.hibernate.ddl-auto = update
      ```
      
 6. Make the following changes in PostService.java
 
    - Update the absolute path of "media" in your pc.
    ```java
       final static String POST_IMAGE_PATH = "absolute_path_of_media_folder";
    ```
    
    - Example: if you have cloned fossee-task on your desktop, then the absolute path will be:
      "/home/pc_name/Desktop/fossee-task/task-2/tblog/media/";
 
 4. Build the project and run on Tomcat server.
 
 5. Create an account using Register tab.
 
 6. To add new post you need to be logged in. So register first.
 
 7. One can view/edit/delete without logging in.
        


    
    
    


