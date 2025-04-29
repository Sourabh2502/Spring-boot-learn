package com.example.demo.controllers;

import com.example.demo.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path= "employee")
public class EmployeeController {
//    @GetMapping(path= "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "heloo seceretmessage";
//    }

    @GetMapping(path= "/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable Long employeeId ){
        return new EmployeeDto(employeeId, "Sourabh","aerosourabh92@gmail.com", 23, LocalDate.of(2024,01,02), true);

    }
    @GetMapping
    public String getEmployee(@RequestParam(required = false) Integer age){
        return "My age is"+age;
    }
}
