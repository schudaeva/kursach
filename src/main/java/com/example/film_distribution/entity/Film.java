package com.example.film_distribution.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; // аннотация для работы со столбцами в скл, id указывается автоматически
import jakarta.persistence.GenerationType; // класс отвечающих за тип данных перечисление
import jakarta.persistence.Id; // указание на первичный ключ

@Entity //аннотация, укзание на то что класс является сущностью и относится к orm jpa
public class Film {
    private Long id;
    private String f_name;
    private String f_country;
    private String f_genres;
    private String f_director;
    private Long f_length;
    private String premiere_date;
    private String age_limit;
    private String f_language;
    private Long year;
    private String description;
    private String actors;
    private String poster;
    public Film(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_country() {
        return f_country;
    }

    public void setF_country(String f_country) {
        this.f_country = f_country;
    }

    public String getF_genres() {
        return f_genres;
    }

    public void setF_genres(String f_genres) {
        this.f_genres = f_genres;
    }

    public String getF_director() {
        return f_director;
    }

    public void setF_director(String f_director) {
        this.f_director = f_director;
    }

    public Long getF_length() {
        return f_length;
    }

    public void setF_length(Long f_length) {
        this.f_length = f_length;
    }

    public String getPremiere_date() {
        return premiere_date;
    }

    public void setPremiere_date(String premiere_date) {
        this.premiere_date = premiere_date;
    }

    public String getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(String age_limit) {
        this.age_limit = age_limit;
    }

    public String getF_language() {
        return f_language;
    }

    public void setF_language(String f_language) {
        this.f_language = f_language;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}

