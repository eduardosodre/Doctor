package br.com.project.domain.doctor;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, String>,
    JpaSpecificationExecutor<DoctorEntity> {
 
    List<DoctorEntity> findByCrm(final String crm);
}
