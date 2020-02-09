package com.luhuihahaha.community.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


public class GreetingController {

    @GetMapping("/greet")
    @ResponseBody
    public String greeting(@RequestParam(name = "name") String name, Model model){

        model.addAttribute("name",name);
        return "hello";

    }

}
