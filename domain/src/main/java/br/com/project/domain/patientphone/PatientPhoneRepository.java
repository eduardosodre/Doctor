package br.com.project.domain.patientphone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientPhoneRepository extends JpaRepository<PatientPhoneEntity, String>,
    JpaSpecificationExecutor<PatientPhoneEntity> {

}
