package com.investmentapp.kafkaconsumerservice.controller;


import com.investmentapp.kafkaconsumerservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public String sendEmail() {
        emailService.sendEmail("hrsht.jn@gmail.com","test","hello 1");
        return "Success";
    }
}
