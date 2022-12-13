package dev.coder.departmentservice.service;

import dev.coder.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(String departmentCode);
}
