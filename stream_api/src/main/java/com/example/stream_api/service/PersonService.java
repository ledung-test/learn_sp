package com.example.stream_api.service;

import com.example.stream_api.dao.PersonDAO;
import com.example.stream_api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    public List<Person> getAll(){
        return personDAO.getAll();
    }
    public List<Person> sortPeopleByFullName(){
        return personDAO.sortPeopleByFullName();
    }
    public List<String> getSortedJobs(){
        return personDAO.getSortedJobs();
    }
    public List<String> getSortedCities(){
        return personDAO.getSortedCities();
    }

}
