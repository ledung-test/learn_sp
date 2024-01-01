package com.example.stream_api.service;

import com.example.stream_api.dao.PersonDAO;
import com.example.stream_api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Map<String, List<Person>> groupPeopleByCity(){
        return personDAO.groupPeopleByCity();
    }

    public Map<String, Integer> groupJobByCount(){
        return personDAO.groupJobByCount();
    }

    public List<Person> aboveAverageSalary(){
        return personDAO.aboveAverageSalary();
    }
    public Person longestName(){
        return personDAO.longestName();
    }
}
