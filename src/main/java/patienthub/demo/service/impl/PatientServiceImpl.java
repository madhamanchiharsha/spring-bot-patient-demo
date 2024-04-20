package patienthub.demo.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import patienthub.demo.dao.Patient;
import patienthub.demo.customExceptions.CustomException;
import patienthub.demo.repository.IPatientRepository;
import patienthub.demo.service.IPatientService;
import patienthub.demo.util.Constants;
import patienthub.demo.util.Exceptions;
import patienthub.demo.util.JavaRegexCheck;

import java.util.List;
import java.util.UUID;

@Service
@Configuration
public class PatientServiceImpl implements IPatientService {
    private static final Logger log = LogManager.getLogger(PatientServiceImpl.class);
    @Autowired
    IPatientRepository patientRepository;

    @Autowired
    JavaRegexCheck javaRegexCheck;
    @Override
    public Patient createPatient(Patient patient) throws CustomException {
        try {
            if(javaRegexCheck.javaRegexChecking(patient.getMobileNumber(), Constants.MOBILE_REGEX)){
                throw new CustomException(Exceptions.MOBILE_NUMBER_INCORRECT.toString());
            }
            if(javaRegexCheck.javaRegexChecking(patient.getEmail(),Constants.EMAIL_REGEX)){
                throw new CustomException(Exceptions.EMAIL_INCORRECT.toString());
            }
            return patientRepository.save(patient);
        } catch (Exception ex){
            throw ex;
        }
    }

    @Override
    @Cacheable("patient")
    public Patient getPatientById(String patientId) throws CustomException {
        Patient patient = patientRepository.findById(UUID.fromString(patientId)).orElse(null);
        if(patient.getPatientId() == null){
            throw new CustomException(Exceptions.PATIENT_NOT_FOUND.toString());
        }
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Patient patient,String patientId) throws CustomException {
        if(javaRegexCheck.javaRegexChecking(patient.getMobileNumber(), Constants.MOBILE_REGEX)){
            throw new CustomException(Exceptions.MOBILE_NUMBER_INCORRECT.toString());
        }
        if(javaRegexCheck.javaRegexChecking(patient.getEmail(),Constants.EMAIL_REGEX)){
            throw new CustomException(Exceptions.EMAIL_INCORRECT.toString());
        }
        Patient existingPatient = patientRepository.findById(UUID.fromString(patientId)).orElse(null);
        if(existingPatient != null){
            existingPatient.setPatientName(patient.getPatientName());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setMobileNumber(patient.getMobileNumber());
        } else {
            throw new CustomException(Exceptions.PATIENT_NOT_FOUND.toString());
        }
        return patientRepository.save(existingPatient);
    }

    @Override
    @CacheEvict(value="patient", key="#patientId")
    public void deletePatient(UUID patientId) {
        try{
        patientRepository.deleteById(patientId);
        } catch (Exception ex){
            throw ex;
        }
    }
}
