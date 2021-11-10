package smarttraffic.violation_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.repository.ViolationRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user", roles = {"ADMIN"})
public class ViolationControllerTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ViolationRepository violationRepository;

//    @MockBean
//    private AzureService azureService;
    @Autowired(required = false)
    private SecurityUtils securityUtils;

    public static ViolationDTO createViolationDTO() {
        ViolationDTO violationDTO = new ViolationDTO();
        violationDTO.setNumber("Test");
        violationDTO.setPrice(100);

        return violationDTO;
    }


//    @Test
//    @Transactional
//    void getViolation() throws Exception {
//        violationRepository.saveAndFlush(createViolationEntity());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/speed")
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + securityUtils.getUserJWT()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$.[*].name").value(hasViolation("Test")));
//
//    }

//    @ParameterizedTest
//    @ValueSource(ints = {1})
//    @Transactional
//    void getViolation(int id) throws Exception {
//        Violation violation = violationRepository.saveAndFlush(createViolationEntity());
//        mockMvc.perform(MockMvcRequestBuilders.get("/speed/{number}", violation.getNumber())
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + securityUtils.getAdminJWT()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$.id").value(violation.getId().intValue()));
//    }

//    @Test
//    void renderViolation() {
//        BDDMockito.given(azureService.isRendering()).willReturn(true);
//
//        assertThat(azureService.isRendering()).isEqualTo(true);
//    }

    public static Violation createViolationEntity() {
        Violation violation = new Violation();
        violation.setNumber("Test");
        violation.setPrice(100);

        return violation;
    }

    @Test
    @Transactional
    void createViolation() throws Exception {
        long countBeforeCreate = violationRepository.count();

        ViolationDTO violationDTO = createViolationDTO();
        mockMvc.perform(MockMvcRequestBuilders.post("/violation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(violationDTO)))
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + securityUtils.getAdminJWT())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").exists());

        assertThat(violationRepository.count()).isEqualTo(countBeforeCreate + 1);
    }
}

