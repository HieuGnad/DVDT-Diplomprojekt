package at.spengergasse.bloodpressurecheck;

import at.spengergasse.bloodpressurecheck.model.Narrative;
import at.spengergasse.bloodpressurecheck.model.Patient;
import at.spengergasse.bloodpressurecheck.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

// Macht aus der Klasse eine Testklasse
@SpringBootTest
// Darüber werden Anfragen an den Controller geschickt und die Antworten ausgewertet.
@AutoConfigureMockMvc
public class PatientControllerTest {
    @Autowired MockMvc mockMvc;

    @Autowired ObjectMapper om;

    @Autowired PatientRepository patientRepository;

    @Autowired private EntityManager entityManager;

    // Erster Test, um alle Patienten unter der URL /api/patient mit GET abzufragen.
    // andExpect überprüft, ob der zurückgegebene Status 200 (OK) ist.
    @Test
    public void getAllPatients() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/patient"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Ein einzelner Patient wird mit der id mit GET abgefragt. Dabei muss der Patient mit der id in
    // der DB existieren.
    // im import.sql muss dieser Patient somit eingefügt werden.
    // andExpect überprüft, ob der zurückgegebene Status 200 (OK) ist.
    @Test
    @Transactional
    public void getAPatient() throws Exception {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        val id = patientRepository.save(patient).getId();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/patient/" + patient.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Es wird ein neuer Patient mit POST an den Controller geschickt und somit in der DB gespeichert.
    // Wir können die Methode aus PatientRepositoryTest, die uns eine Patienten Instanz erzeugt auch
    // hier verwenden.
    // Der erwartete Rückgabecode ist "CREATED" Also 201.
    @Test
    @Transactional
    public void postAPatient() throws Exception {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        Entities.unsetAllIds(patient);
        String json = om.writeValueAsString(patient);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/patient/")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // PUT aktualisiert einen Patienten. Dieser muss somit bereits in der DB existieren (über
    // import.sql)
    // Die id im Patienten und die id in der URL sollten gesetzt sein und müssen in der DB existieren.
    // Wir erwarten ein 200- OK für einen aktualisierten Patienten.
    // Kein 201 CREATED, sonst wäre der Patient neu angelegt worden.
    @Test
    @Transactional
    public void putAPatient() throws Exception {
        Patient patient = patientRepository.save(PatientRepositoryTest.returnOnePatient());
        val id = patient.getId();
        Entities.unsetAllIds(patient);
        // Ein paar Attribute werden geändert
        patient.setActive(!patient.getActive());
        patient.setGender(Patient.GenderCode.unknown);

        String json = om.writeValueAsString(patient);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/api/patient/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Der Patient wird geöscht.
    // Die id muss es bereits geben.
    // Erwartete Antwort ist 200 (OK)
    @Test
    @Transactional
    public void deleteAPatient() throws Exception {
        Patient p = PatientRepositoryTest.returnOnePatient();
        Patient pWithId = patientRepository.save(p);

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/patient/" + pWithId.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
