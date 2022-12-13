package dev.coder.organizationservice.service.impl;

import dev.coder.organizationservice.dto.OrganizationDto;
import dev.coder.organizationservice.entity.Organization;
import dev.coder.organizationservice.mapper.OrganizationMapper;
import dev.coder.organizationservice.repository.OrganizationRepository;
import dev.coder.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization save = repository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(save);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organizationByOrganizationCode = repository.findOrganizationByOrganizationCode(
            organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organizationByOrganizationCode);
    }
}
