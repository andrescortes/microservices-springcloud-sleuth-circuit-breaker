package dev.coder.employeeservice.service.impl;

import dev.coder.employeeservice.dto.APIResponseDto;
import dev.coder.employeeservice.dto.DepartmentDto;
import dev.coder.employeeservice.dto.EmployeeDto;
import dev.coder.employeeservice.dto.OrganizationDto;
import dev.coder.employeeservice.entity.Employee;
import dev.coder.employeeservice.mapper.EmployeeMapper;
import dev.coder.employeeservice.repository.EmployeeRepository;
import dev.coder.employeeservice.service.EmployeeService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;
    //    private RestTemplate restTemplate;
    private WebClient webClient;
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee save = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(save);
    }

    @Override
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("inside getEmployeeId() method");
        Employee employeeOptional = employeeRepository.findById(employeeId).get();
        EmployeeDto employeeDto1 = EmployeeMapper.mapToEmployeeDto(employeeOptional);

/*        ResponseEntity<DepartmentDto> entity = restTemplate.getForEntity(
            "http://localhost:8080/api/departments/".concat(employeeOptional.getDepartmentCode()),
            DepartmentDto.class);*/

        DepartmentDto departmentDto = webClient.get().uri(
                "http://localhost:8080/api/departments/".concat(employeeOptional.getDepartmentCode()))
            .retrieve()
            .bodyToMono(DepartmentDto.class)
            .block();

        OrganizationDto organizationDto = webClient.get().uri(
                "http://localhost:8083/api/organizations/".concat(
                    employeeOptional.getOrganizationCode()))
            .retrieve()
            .bodyToMono(OrganizationDto.class)
            .block();

        //DepartmentDto departmentDto = apiClient.getDepartment(employeeOptional.getDepartmentCode());

//        DepartmentDto departmentDto = entity.getBody();
        APIResponseDto apiResponseDto = new APIResponseDto();

        apiResponseDto.setEmployeeDto(employeeDto1);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
        LOGGER.info("inside getDefaultDepartment() method");
        Employee employeeOptional = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        APIResponseDto apiResponseDto = new APIResponseDto();

        EmployeeDto employeeDto1 = EmployeeMapper.mapToEmployeeDto(employeeOptional);

        apiResponseDto.setEmployeeDto(employeeDto1);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }
}
