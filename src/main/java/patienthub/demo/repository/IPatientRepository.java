package patienthub.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patienthub.demo.dao.Patient;

import java.util.UUID;

public interface IPatientRepository extends JpaRepository<Patient, UUID> {

}
