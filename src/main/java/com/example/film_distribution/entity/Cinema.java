package com.example.film_distribution.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; // аннотация для работы со столбцами в скл, id указывается автоматически
import jakarta.persistence.GenerationType; // класс отвечающих за тип данных перечисление
import jakarta.persistence.Id; // указание на первичный ключ

@Entity
public class Cinema {
    private Long id;
    private String name;
    private String address;
    private String metro;
    private Float rating;

    public Cinema(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
