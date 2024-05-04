package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${spring.application.name}")
    protected String applicationName;

    @Value("${spring.profiles.active}")
    protected String springProfilesActive;

    @RequestMapping({"/"})
    public String index() {
        return this.applicationName + ":" + this.springProfilesActive;
    }
}