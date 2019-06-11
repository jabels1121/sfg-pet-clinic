package com.jaybe.sfgpetclinic.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    @RequestMapping(path = {"", "/", "/index", "/index.html"})
    public String listOfOwners(){
        return "owners/index";
    }

}
