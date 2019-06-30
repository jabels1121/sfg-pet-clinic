package com.jaybe.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(path = {"", "/", "index", "index.html"})
    public String index() {
        return "index";
    }

    @GetMapping(path = {"/oups"})
    public String findOwners() {
        return "not_implemented";
    }
}
