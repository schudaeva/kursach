package com.example.film_distribution.repository;

import java.util.List;
// Repository – указывает, что класс используется для задания перечня необходимых работ по поиску, получению и сохранению данных.
import com.example.film_distribution.entity.Session;
import com.example.film_distribution.entity.Cinema;
import com.example.film_distribution.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
import org.springframework.data.jpa.repository.Query; // аннотация скл запроса
public interface SessionRepository extends JpaRepository<Session, Long>{
    @Query("SELECT p FROM Session p WHERE CONCAT(p.id, ' ', p.film_id, ' ',p.film_name,' ', p.cinema_id, ' ', p.cinema_name,' '," +
            " p.start_date,' ', p.time, ' ', p.ticket_num) LIKE %?1%")
    List<Session> search(String keyword);
}
// p - параметр, взаимодействуем с классом device

