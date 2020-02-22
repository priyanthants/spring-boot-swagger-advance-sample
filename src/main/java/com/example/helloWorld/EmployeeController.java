package com.example.helloWorld;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Api(value="Employee Management System", description="Curd Operation for Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    @ApiOperation(value = "View a list of available employees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{employeeId}")
    @ApiOperation(value = "Get an employee by Id")
    public Employee getEmployeeByID( @ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    @ApiOperation(value = "Add a new employee")
    public Employee createEmployee(@ApiParam(value = "Employee object store in database table", required = true) @Valid @RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    @ApiOperation(value = "Update an employee")
    public Employee updateEmployee(@ApiParam(value = "Employee Id to update employee object", required = true) @PathVariable Long employeeId,
                                   @ApiParam(value = "Update employee object", required = true) @Valid @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId,employee);
    }

    @DeleteMapping("/{employeeId}")
    @ApiOperation(value = "Delete an employee")
    public Boolean deleteEmployee(@ApiParam(value = "Employee Id from which employee object will delete from database table", required = true) @PathVariable Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

}
