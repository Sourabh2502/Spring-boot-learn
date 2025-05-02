package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.repositries.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDto employeeDto;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeDto employeeDto, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeDto = employeeDto;
        this.modelMapper = modelMapper;
    }



    public EmployeeDto getEmployeeById(Long id){
        EmployeeEntity employeeEntity= employeeRepository.findById(id).orElse(null);

       return modelMapper.map(employeeEntity,EmployeeDto.class);
    }

    public List<EmployeeDto> getEmployee(){
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        System.out.println(employeeEntityList);
        return employeeEntityList
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createEmployee(EmployeeDto inputEmployee){

        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity employeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }
}
