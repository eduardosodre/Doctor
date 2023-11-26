package br.com.project.domain.patient;

import java.util.UUID;
import java.util.List;
import br.com.project.domain.validation.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, String>,
	JpaSpecificationExecutor<PatientEntity> {

}
