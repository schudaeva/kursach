package com.example.film_distribution.service;

import java.util.List;
import com.example.film_distribution.entity.Session;
import com.example.film_distribution.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private SessionRepository repo;

    public List<Session> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }


    public void save(Session session) {
        repo.save(session);
    }

    public Session get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
