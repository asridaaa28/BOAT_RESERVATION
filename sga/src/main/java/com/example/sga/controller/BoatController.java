package com.example.sga.controller;

import com.example.sga.model.Boat;
import com.example.sga.service.BoatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;*/
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Controller
public class BoatController {

    @Autowired
    private BoatService boatService;

    @GetMapping("/boats")
    public String getBoats(Model model) {
        List<Boat> boats = boatService.getAllBoats();
        model.addAttribute("boats", boats);
        return "boats";
    }
}