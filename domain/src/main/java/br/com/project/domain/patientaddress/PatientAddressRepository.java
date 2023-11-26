package br.com.project.domain.patientaddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAddressRepository extends JpaRepository<PatientAddressEntity, String>,
    JpaSpecificationExecutor<PatientAddressEntity> {

}
