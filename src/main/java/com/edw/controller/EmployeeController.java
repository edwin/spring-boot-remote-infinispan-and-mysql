package com.edw.controller;

import com.edw.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *  com.edw.controller.EmployeeController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 23 Nov 2025 15:15
 */
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity getEmployeeById (@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/employee/find-by-firstname-and-lastname/{firstname}/{lastname}")
    public ResponseEntity getEmployeeByFirstnameAndLastname (@PathVariable String firstname, @PathVariable String lastname) {
        return ResponseEntity.ok(employeeService.searchEmployeeByFirstnameAndLastname(firstname, lastname));
    }

    @GetMapping("/employee/find-by-gender/{gender}")
    public ResponseEntity getEmployeeByGender (@PathVariable String gender) {
        return ResponseEntity.ok(employeeService.searchEmployeeByGender(gender));
    }

    @GetMapping("/employee/get-all-cache")
    public ResponseEntity getEmployeeByGender () {
        return ResponseEntity.ok(employeeService.searchAllCache());
    }
}
