package com.example.mi_api.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "photos")

public class Photos {
    
    public Photos(){
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Integer photo_id; 

    @Column(name = "title")
	private String title; 

    @Column(name = "description")
	private String description;

    @Column(name = "date")
	private LocalDateTime date; 

    @Column(name = "url")
	private String url; 

    @Column(name = "visibility")
	private String visibility; 

    @Column(name = "user_id")
	private Integer user_id;

    public Photos(Integer photo_id, String title, String description, LocalDateTime date, String url, String visibility,
            Integer user_id) {
        this.photo_id = photo_id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.url = url;
        this.visibility = visibility;
        this.user_id = user_id;
    }

    public Integer getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
	
    

}
