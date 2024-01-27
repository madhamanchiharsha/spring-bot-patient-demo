package patienthub.demo.service;

import patienthub.demo.dao.Patient;
import patienthub.demo.exception.CustomException;

import java.util.List;
import java.util.UUID;

public interface IPatientService {
    Patient createPatient(Patient patient) throws CustomException;
    Patient getPatientById(String patientId) throws CustomException;
    List<Patient> getAllPatients();
    Patient updatePatient(Patient patient,String patientId) throws CustomException;

    void deletePatient(UUID patientId);
}
