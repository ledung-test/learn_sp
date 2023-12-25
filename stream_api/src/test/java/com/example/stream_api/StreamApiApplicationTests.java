package com.example.stream_api;

import com.example.stream_api.dao.PersonDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StreamApiApplicationTests {
    @Autowired
    private PersonDAO personDAO;

    @Test
    void contextLoads() {
        personDAO.printListPeople(personDAO.getAll());
    }

}
