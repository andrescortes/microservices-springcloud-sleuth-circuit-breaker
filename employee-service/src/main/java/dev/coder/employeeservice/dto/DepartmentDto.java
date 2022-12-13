package dev.coder.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link dev.coder.employeeservice.entity.Employee} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DepartmentDto {

    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

}
