package com.example.movie.controller.admin;

import com.example.movie.entity.Actor;
import com.example.movie.entity.Director;
import com.example.movie.service.admin.AdminActorService;
import com.example.movie.service.admin.AdminDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/actors")
public class AdminActorController {
    @Autowired
    private AdminActorService adminActorService;
    @GetMapping
    public String getActor(Model model){
        List<Actor> actors = adminActorService.getAllActor();
        model.addAttribute("actors", actors);
        return "admin/actor/actor";
    }
    @GetMapping("/create")
    public String createActor(){
        return "admin/actor/create";
    }
    @GetMapping("/{id}/detail")
    public String detailActor(Model model, @PathVariable Integer id){
        Actor actor = adminActorService.detailActor(id);
        model.addAttribute("actor", actor);
        return "admin/actor/detail";
    }
}
