package com.ocean.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ocean.demo.dao.EmployeeRepository;
import com.ocean.demo.model.Employee;

@Service
@Transactional
public class EmployeeServiceImpl {
	@Autowired
    EmployeeRepository empRepo;
 
    //@Override
    public List<Employee> getEmployees() {
        return (List<Employee>) empRepo.findAll();
    }
    //@Override
    public Optional<Employee> getEmployeeById(int empid) {
        return empRepo.findById(empid);
    }
    //@Override
    public Employee addNewEmployee(Employee emp) {
        return empRepo.save(emp);
    }
    //@Override
    public Employee updateEmployee(Employee emp) {
        return empRepo.save(emp);
    }
    //@Override
    public void deleteEmployeeById(int empid) {
        empRepo.deleteById(empid);
    }
    //@Override
    public void deleteAllEmployees() {
        empRepo.deleteAll();
    }
}
