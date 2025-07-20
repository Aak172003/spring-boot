package com.selfupgrade.FirstSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//This refers specialise version of controller
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return  "Hello World";
    }

    @GetMapping("/")
    public String errorPage(){
        return  "This is Error Page";
    }

    @PostMapping("/hello")
    public String helloPost(@RequestBody String name){
        System.out.println("Received name: " + name); // Console log
        return "Hello " + name + " ! ";
    }
}
