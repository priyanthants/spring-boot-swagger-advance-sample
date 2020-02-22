package com.example.helloWorld;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employeeList=new ArrayList<>();

    public EmployeeService() {
        this.employeeList.add(new Employee(1L,"John","Wick",new Address("No 420","USA")));
        this.employeeList.add(new Employee(2L,"John","Wick",new Address("No 420","USA")));
    }

    public List<Employee> getAllEmployee(){
        return employeeList;
    }

    public Employee getEmployeeById(Long employeeId){
        return employeeList.stream().filter(employee -> employee.getEmployeeId().equals(employeeId)).findFirst().orElseThrow(()->new  EmployeeNotFound());
    }

    public Employee createEmployee(Employee employee){
        employee.setEmployeeId(Long.valueOf(employeeList.size()+1));
        employeeList.add(employee);
        return employee;
    }

    public Employee updateEmployee(Long employeeId,Employee employee){
        Employee existingEmployee=employeeList.stream().filter(emp -> emp.getEmployeeId().equals(employeeId)).findFirst().orElseThrow(()->new  EmployeeNotFound());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setAddress(employee.getAddress());
        return existingEmployee;
    }

    public Boolean deleteEmployee(Long employeeId){
        Employee existingEmployee=employeeList.stream().filter(emp -> emp.getEmployeeId().equals(employeeId)).findFirst().orElseThrow(()->new  EmployeeNotFound());
        return employeeList.remove(existingEmployee);
    }
}

@ApiModel(description = "All details about the Employee.")
class Employee{

    @ApiModelProperty(notes = "The database generated employee ID",required = true)
    private Long employeeId;
    @ApiModelProperty(notes = "The employee first name",required = true)
    private String firstName;
    @ApiModelProperty(notes = "The employee last name",required = true)
    private String lastName;
    @ApiModelProperty(notes = "The employee address",required = true)
    private Address address;

    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, Address address) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Employee(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

class Address{
    @ApiModelProperty(notes="Address Line of the Address",required = true)
    private String addressLine;
    @ApiModelProperty(notes="country of the Address")
    private String country;

    public Address() {
    }

    public Address(String addressLine, String country) {
        this.addressLine = addressLine;
        this.country = country;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

class EmployeeNotFound  extends RuntimeException {

    public EmployeeNotFound() {
        super("Employee Not Found");
    }
}
