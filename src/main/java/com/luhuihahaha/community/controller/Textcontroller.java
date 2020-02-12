package com.luhuihahaha.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Textcontroller {

    @RequestMapping("/text")
    public String textss(){
        return "text";
    }
}
