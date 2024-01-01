package com.example.stream_api.controller;

import com.example.stream_api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping("/")
    public String getHome(){
        return "index";
    }
    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("personList", personService.getAll());
        return "getAll";
    }
    @GetMapping("/sortPeopleByFullName")
    public String sortPeopleByFullName(Model model){
        model.addAttribute("sortByName", personService.sortPeopleByFullName());
        return "sortPeopleByFullName";
    }
    @GetMapping("/getSortedJobs")
    public String getSortedJobs(Model model){
        model.addAttribute("sortByJobs", personService.getSortedJobs());
        return "getSortedJobs";
    }
    @GetMapping("/getSortedCities")
    public String getSortedCities(Model model){
        model.addAttribute("sortByCities", personService.getSortedCities());
        return "getSortedCities";
    }
    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model){
        model.addAttribute("groupPeopleByCity", personService.groupPeopleByCity());
        return "groupPeopleByCity";
    }
    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model){
        model.addAttribute("groupJobByCount", personService.groupJobByCount());
        return "groupJobByCount";
    }
    @GetMapping("/aboveAverageSalary")
    public String aboveAverageSalary(Model model){
        model.addAttribute("aboveAverageSalary", personService.aboveAverageSalary());
        return "aboveAverageSalary";
    }
    @GetMapping("/longestName")
    public String longestName(Model model){
        model.addAttribute("longestName", personService.longestName());
        return "longestName";
    }
}
