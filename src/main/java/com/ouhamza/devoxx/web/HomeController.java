package com.ouhamza.devoxx.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ouhamza
 */

@RestController
public class HomeController {


    @Value("${conference.name:devoxx}")
    private String reference;

    @Secured("ROLE_HERO")
    @RequestMapping("/")
    public String home(){
        return "Hello " + reference;
    }
}
