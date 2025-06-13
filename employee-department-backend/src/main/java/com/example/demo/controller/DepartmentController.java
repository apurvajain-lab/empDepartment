package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{deptId}/employees")
    public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable String deptId) {
        return departmentService.getEmployeesByDepartment(deptId);
    }

    @PostMapping("/{deptId}/employees")
    public ResponseEntity<EmployeeDTO> addEmployee(@PathVariable String deptId, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(departmentService.addEmployeeToDepartment(deptId, employeeDTO));
    }

    @DeleteMapping("/{deptId}/employees/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String deptId, @PathVariable String empId) {
        departmentService.deleteEmployeeFromDepartment(deptId, empId);
        return ResponseEntity.noContent().build();
    }
}
