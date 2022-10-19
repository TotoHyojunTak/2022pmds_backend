package com.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value="/helloworld", method= RequestMethod.GET)
    public String helloworld(){
        return "backend - helloworld";
    }

}
