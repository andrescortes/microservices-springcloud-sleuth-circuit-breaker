package dev.coder.organizationservice.repository;

import dev.coder.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findOrganizationByOrganizationCode(String organizationCode);
}
