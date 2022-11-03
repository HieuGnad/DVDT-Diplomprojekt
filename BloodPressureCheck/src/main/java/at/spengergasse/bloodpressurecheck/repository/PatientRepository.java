package at.spengergasse.bloodpressurecheck.repository;
import at.spengergasse.bloodpressurecheck.model.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientRepository extends
        PagingAndSortingRepository<Patient, String> {
}