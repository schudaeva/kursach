package com.example.film_distribution.service;

import java.util.List;

import com.example.film_distribution.entity.Film;
import com.example.film_distribution.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired; // связь всех зависимостей
import org.springframework.stereotype.Service;
// Service – указывает, что класс является сервисом для реализации бизнес логики.

@Service
public class FilmService {
    @Autowired
    private FilmRepository repo;

    public List<Film> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Film film) {repo.save(film);}

    public Film get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
