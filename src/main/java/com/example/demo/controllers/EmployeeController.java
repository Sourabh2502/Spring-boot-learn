package com.example.demo.controllers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "employeeId") Long employeeId ){
        Optional<EmployeeDto> employeeDto= employeeService.getEmployeeById(employeeId);

        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                        .orElse(ResponseEntity.notFound().build());

    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee(@RequestParam(required = false) Integer age){
        return ResponseEntity.ok(employeeService.getEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto inputEmployee){
        EmployeeDto savedEmployee= employeeService.createEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @PutMapping(path= "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody EmployeeDto employeeDto, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDto));
    }

    @DeleteMapping(path= "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted= employeeService.deleteEmployeeById(employeeId);
        if (gotDeleted) return ResponseEntity.ok(true);
        return  ResponseEntity.notFound().build();
    }
   @PatchMapping(path= "/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,@PathVariable Long employeeId){
      EmployeeDto employeeDto=employeeService.updatePartialEmployeeById(employeeId, updates);
      if (employeeDto== null) return ResponseEntity.notFound().build();
      return ResponseEntity.ok(employeeDto);
   }

}
