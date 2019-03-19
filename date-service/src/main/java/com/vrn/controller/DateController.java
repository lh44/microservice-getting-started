package com.vrn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("date")
public class DateController {

    @GetMapping
    @ResponseBody
    public String dateNow() {
        return String.format("The current datetime is %s, and I'm completely functional", new Date());
    }
 }
