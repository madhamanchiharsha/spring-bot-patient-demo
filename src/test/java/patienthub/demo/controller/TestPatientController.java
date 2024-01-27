package patienthub.demo.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import patienthub.demo.dao.Patient;
import patienthub.demo.exception.CustomException;
import patienthub.demo.service.IPatientService;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPatientController {

    private MockMvc mockMvc;

    @InjectMocks
    PatientController patientController;

    @Mock
    IPatientService patientService;

    private final String GET_ALL_URL = "/patient";
    private final String GET_URL = "/patient/id/{id}";
    private final String POST_URL = "/patient";
    private final String PUT_URL = "/patient/id/{id}";
    private final String DELETE_URL = "/patient/id/{id}";

    Patient patient = null;

    @BeforeAll
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
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
    public void TestGetPatientById() throws Exception {
        String get_url = GET_URL.replace("{id}",patient.getPatientId().toString());
        when(patientService.getPatientById(patient.getPatientId().toString())).thenReturn(patient);
        mockMvc.perform(get(get_url).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is2xxSuccessful()).andReturn();
    }


}
