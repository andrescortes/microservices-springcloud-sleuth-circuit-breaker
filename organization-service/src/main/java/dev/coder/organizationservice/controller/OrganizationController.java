package dev.coder.organizationservice.controller;

import dev.coder.organizationservice.dto.OrganizationDto;
import dev.coder.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService service;

    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(
        @RequestBody OrganizationDto organizationDto) {
        OrganizationDto saveOrganization = service.saveOrganization(organizationDto);
        return new ResponseEntity<>(saveOrganization, HttpStatus.CREATED);
    }

    @GetMapping("{organization-code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(
        @PathVariable(value = "organization-code") String organizationCode) {
        OrganizationDto organizationByCode = service.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(organizationByCode, HttpStatus.OK);
    }
}
