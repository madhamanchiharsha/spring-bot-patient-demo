package patienthub.demo.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import patienthub.demo.dao.Patient;
import patienthub.demo.exception.CustomException;
import patienthub.demo.repository.IPatientRepository;
import patienthub.demo.service.impl.PatientServiceImpl;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPatientServiceImpl {
    @Mock
    IPatientRepository patientRepository;

    @InjectMocks
    PatientServiceImpl patientService;

    Patient patient = null;

    @BeforeAll
    public void setup(){
        MockitoAnnotations.openMocks(this);
        initialize();
    }

    private void initialize(){
        patient = new Patient();
        patient.setPatientId(UUID.randomUUID());
        patient.setPatientName("test");
        patient.setMobileNumber("2121212121");
        patient.setEmail("hjshjh@gmail.com");
    }

    @Test
    public void TestGetPatientById() throws CustomException {
        when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.ofNullable(patient));
        patientService.getPatientById(patient.getPatientId().toString());
        Mockito.verify(patientRepository);
    }

}
