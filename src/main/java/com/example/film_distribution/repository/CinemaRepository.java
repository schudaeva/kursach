package com.example.film_distribution.repository;


import java.util.List;
// Repository – указывает, что класс используется для задания перечня необходимых работ по поиску, получению и сохранению данных.
import com.example.film_distribution.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
import org.springframework.data.jpa.repository.Query; // аннотация скл запроса
public interface CinemaRepository extends JpaRepository<Cinema, Long>{
    @Query("SELECT p FROM Cinema p WHERE CONCAT(p.name, ' ', p.address, ' ', p.metro, ' ', p.rating) LIKE %?1%")
    List<Cinema> search(String keyword);
}