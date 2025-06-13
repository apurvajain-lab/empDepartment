package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository deptRepo;

    @Autowired
    private EmployeeRepository empRepo;

    public List<DepartmentDTO> getAllDepartments() {
        return deptRepo.findAll().stream().map(dept -> {
            DepartmentDTO dto = new DepartmentDTO();
            dto.setId(dept.getId());
            dto.setName(dept.getName());
            dto.setLocation(dept.getLocation());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByDepartment(String deptId) {
        Department dept = deptRepo.findById(deptId).orElseThrow();
        return dept.getEmployees().stream().map(emp -> {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setId(emp.getId());
            dto.setName(emp.getName());
            dto.setEmail(emp.getEmail());
            dto.setPosition(emp.getPosition());
            dto.setSalary(emp.getSalary());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public EmployeeDTO addEmployeeToDepartment(String deptId, EmployeeDTO dto) {
        Department dept = deptRepo.findById(deptId).orElseThrow();
        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setPosition(dto.getPosition());
        emp.setSalary(dto.getSalary());
        emp.setDepartment(dept);
        emp = empRepo.save(emp);
        return dto;
    }

    @Transactional
    public void deleteEmployeeFromDepartment(String deptId, String empId) {
        empRepo.deleteById(empId);
    }
}
