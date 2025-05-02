package com.example.demo.controllers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path= "employee")
public class EmployeeController {
//    @GetMapping(path= "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "heloo seceretmessage";
//    }
private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path= "/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long employeeId ){
        return employeeService.getEmployeeById(employeeId);

    }
    @GetMapping
    public List<EmployeeDto> getEmployee(@RequestParam(required = false) Integer age){
        System.out.println("hii");
        return employeeService.getEmployee();
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto inputEmployee){
        System.out.println("hii");
        return employeeService.createEmployee(inputEmployee);
    }
}
