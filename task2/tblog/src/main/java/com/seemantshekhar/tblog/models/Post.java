package com.seemantshekhar.tblog.models;

import javax.persistence.*;


/**
 * Post Model for Blog posts
 * Id will be auto generated for new entries
 * Name of the table to store posts is "posts"
 */
@Entity
@Table(name = "posts")
public class Post {

    //Constructor for Post Class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String author;
    private String description;
    private String date;
    private String image;

    public Post() {

    }

    //Getters and Setters for Post Class
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
