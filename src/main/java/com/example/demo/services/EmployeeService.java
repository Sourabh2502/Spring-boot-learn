package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.repositries.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }



    public Optional<EmployeeDto> getEmployeeById(Long id){
//        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(id);
//
//       return employeeEntity.map(employeeEntity1->modelMapper.map(employeeEntity1,EmployeeDto.class));

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class));
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
    public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto employeeDto){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);

    }

    public boolean isExistById(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }
public boolean deleteEmployeeById(Long employeeId){
        boolean exists = isExistById(employeeId);
        if(!exists) return false;
        employeeRepository.deleteById(employeeId);
        return true;
}
  public EmployeeDto updatePartialEmployeeById(Long employeeId, Map<String, Object> updates){
        boolean exists = isExistById(employeeId);
        if(!exists) return null;
        EmployeeEntity employeeEntity =employeeRepository.findById(employeeId).get();
      updates.forEach((field,value)->{
        Field fieldToBeUpdated=  ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
        fieldToBeUpdated.setAccessible(true);
        ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
      });
    return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDto.class);
  }
}
