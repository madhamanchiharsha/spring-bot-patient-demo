package patienthub.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patienthub.demo.dao.Patient;
import patienthub.demo.exception.CustomException;
import patienthub.demo.service.IPatientService;
import patienthub.demo.util.Exceptions;

import java.util.List;
import java.util.UUID;

@RestController
public class PatientController {
    private static final Logger log = LogManager.getLogger(PatientController.class);
    @Autowired
    public IPatientService patientService;
    @GetMapping("/patient/id/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") String id){
        Patient patient = null;
        try {
            patient = patientService.getPatientById(id);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return new ResponseEntity<>(patient, HttpStatus.ACCEPTED);
    }

    @GetMapping("/patient")
    public ResponseEntity<List<Patient>> getAllPatients(){
        List<Patient> patientList = null;
        try {
            patientList = patientService.getAllPatients();
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return new ResponseEntity<>(patientList, HttpStatus.ACCEPTED);
    }

    @PostMapping("/patient")
    public ResponseEntity<String> createPatient(@RequestBody Patient patient){
        Patient createdPatient = null;
        try {
            createdPatient = patientService.createPatient(patient);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage().toString(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdPatient != null ? createdPatient.getPatientId().toString() : Exceptions.PATIENT_CREATION_FAILED.toString(), HttpStatus.CREATED);
    }

    @PutMapping("/patient/id/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient,@PathVariable("id") String patientId ){
        Patient updatedPatient = null;
        try {
            if(patientId.isBlank() || patientId.isEmpty()) {
                throw new CustomException(Exceptions.PATIENT_NOT_FOUND.toString());
            }
            log.info("test 1");
            updatedPatient = patientService.updatePatient(patient, patientId);
            log.info("test 11");
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedPatient, HttpStatus.CREATED);
    }

    @DeleteMapping("/patient/id/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") String patientId){
        patientService.deletePatient(UUID.fromString(patientId));
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
}
