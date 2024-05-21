package com.example.film_distribution.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Session {
    private Long id;
    private Long film_id;
    private Long cinema_id;
    private String film_name;
    private String cinema_name;
    private String start_date;
    private String time;
    private Long ticket_num;
    public Session(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public Long getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Long cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getTicket_num() {
        return ticket_num;
    }

    public void setTicket_num(Long ticket_num) {
        this.ticket_num = ticket_num;
    }
}
