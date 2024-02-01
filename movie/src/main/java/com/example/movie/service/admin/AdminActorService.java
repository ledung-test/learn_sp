package com.example.movie.service.admin;

import com.example.movie.entity.Actor;
import com.example.movie.reponsitory.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminActorService {
    @Autowired
    private ActorRepository actorRepository;
    public List<Actor> getAllActor(){
        return actorRepository.findAll();
    }
}
