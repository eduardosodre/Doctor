package br.com.project.domain.patientemail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientEmailRepository extends JpaRepository<PatientEmailEntity, String>,
    JpaSpecificationExecutor<PatientEmailEntity> {

}
