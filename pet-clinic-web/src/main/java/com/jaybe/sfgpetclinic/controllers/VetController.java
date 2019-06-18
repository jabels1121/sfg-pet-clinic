package com.jaybe.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController {

    @RequestMapping(path = {"/","/index", "index.html"})
    public String listVets() {
        return "vets/index";
    }
}
