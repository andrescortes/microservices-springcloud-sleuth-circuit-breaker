package dev.coder.employeeservice.service;


import dev.coder.employeeservice.dto.APIResponseDto;
import dev.coder.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
