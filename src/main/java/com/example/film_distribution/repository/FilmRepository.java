package com.example.film_distribution.repository;


import java.util.List;
// Repository – указывает, что класс используется для задания перечня необходимых работ по поиску, получению и сохранению данных.
import com.example.film_distribution.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
import org.springframework.data.jpa.repository.Query; // аннотация скл запроса
public interface FilmRepository extends JpaRepository<Film, Long>{
    @Query("SELECT p FROM Film p WHERE CONCAT(p.f_name, ' ', p.f_country, ' ', p.f_genres, ' ', p.f_director, ' '," +
            " p.f_length, ' ', p.premiere_date, ' ', p.age_limit, ' ', p.f_language, ' ', p.year, ' ',p.description, ' ', p.actors," +
            " ' ', p.poster) LIKE %?1%")
    List<Film> search(String keyword);
}
// p - параметр, взаимодействуем с классом device

