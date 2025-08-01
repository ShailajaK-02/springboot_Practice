package com.spring.controller;

import com.spring.entity.Employee;
import com.spring.repo.EmployeeRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class myController {

    //get Data By Id API(Endpoint)
    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getDataById(@PathVariable("id") int id){
        List<Employee> employeeList = EmployeeRepo.getAllEmp();
        for(Employee e : employeeList){
            if(id == e.getId()){
                return new ResponseEntity<>(e, HttpStatus.OK);
            }
        }
        Employee e1 = new Employee();
        return new ResponseEntity<>(e1,HttpStatus.NOT_FOUND);
    }

    //get Data By name API
    @GetMapping("/getByName/{name}")
    public ResponseEntity<Employee> getDatabyname(@PathVariable("name") String name){
        List<Employee> employeeList = EmployeeRepo.getAllEmp();
        for(Employee e : employeeList){
            if(e.getFname().equals(name)){
                return new ResponseEntity<>(e,HttpStatus.OK);
            }
        }
        Employee e1 = new Employee();
        return new ResponseEntity<>(e1, HttpStatus.NOT_FOUND);
    }

    //get data by name igonre equalcase
    @GetMapping("/getByNameIgnoreCase/{name}")
    public ResponseEntity<Employee> dataByName(@PathVariable("name") String name){
        List<Employee> employeeList = EmployeeRepo.getAllEmp();
        for(Employee e : employeeList){
            if(e.getFname().equalsIgnoreCase(name)){
                return new ResponseEntity<>(e,HttpStatus.OK);
            }
        }
        Employee e1 = new Employee();
        return new ResponseEntity<>(e1, HttpStatus.NOT_FOUND);
    }

    //get data by city API
    @GetMapping("/getDataByCity/{city}")
    public ResponseEntity<List<Employee>> getDataByCity(@PathVariable("city") String city){
        List<Employee> employeeList = EmployeeRepo.getAllEmp();
        List<Employee> empCityWise = new ArrayList<>();
        for(Employee e : employeeList){
            if(e.getCity().equalsIgnoreCase(city)){
                empCityWise.add(e);
            }
        }
        return new ResponseEntity<>(empCityWise, HttpStatus.OK);
    }

    //getbycity
    @GetMapping("/sorted")
    public ResponseEntity<List<Employee>> getAllemp(@RequestParam(required = false) String city){
        System.err.println("City value is : "+city);
        List<Employee> employeeList = EmployeeRepo.getAllEmp();
        if(city!=null){
            //sort by city
            List<Employee> empCityWise = new ArrayList<>();
            for(Employee e : employeeList){
                if(e.getCity().equalsIgnoreCase(city)){
                    empCityWise.add(e);
                }
            }
            return new ResponseEntity<>(empCityWise, HttpStatus.OK);
        }
        else {
            //return all data
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        }
    }
}
