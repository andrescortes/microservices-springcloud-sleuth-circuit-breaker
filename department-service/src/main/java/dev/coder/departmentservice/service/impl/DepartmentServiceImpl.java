package dev.coder.departmentservice.service.impl;

import dev.coder.departmentservice.dto.DepartmentDto;
import dev.coder.departmentservice.entity.Department;
import dev.coder.departmentservice.mapper.DepartmentMapper;
import dev.coder.departmentservice.repository.DepartmentRepository;
import dev.coder.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //convert department dto to department jpa entity
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(String departmentCode) {
        Department department = departmentRepository.findDepartmentByDepartmentCode(departmentCode);
        return DepartmentMapper.mapToDepartmentDto(department);
    }
}
