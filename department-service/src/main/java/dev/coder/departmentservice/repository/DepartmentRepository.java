package dev.coder.departmentservice.repository;

import dev.coder.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentByDepartmentCode(String departmentCode);
}
