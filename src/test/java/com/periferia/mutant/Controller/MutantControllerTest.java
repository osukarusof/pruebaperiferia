package com.periferia.mutant.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
@Transactional
public class MutantControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private String baseUrl = "/api/v1";

    @BeforeEach
    public void setUp () {
        mockMvc  = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void isNotMutantErrorBadRequestLengthCero () throws Exception {

        String expectedResponse = "{\"message\":\"Validation failed. Check the request parameters.\",\"status\":400,\"data\":[],\"fieldErrors\":{\"adn\":\"DNA array must not be empty and all rows must have the same length\"}}";

        String requestBody = "{\n" +
                "    \"adn\": []\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl +"/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.content().string(containsString(expectedResponse))));
    }

    @Test
    public void isNotMutantErrorBadRequestDnaBlank () throws Exception {

        String expectedResponse = "{\"message\":\"Validation failed. Check the request parameters.\",\"status\":400,\"data\":[],\"fieldErrors\":{\"adn\":\"DNA array must not be empty and all rows must have the same length\"}}";

        String requestBody = "{\n" +
                "    \"adn\": [\"\"]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl +"/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.content().string(containsString(expectedResponse))));
    }

    @Test
    public void isNotMutantErrorBadRequestDnaLengfth () throws Exception {

        String expectedResponse = "{\"message\":\"Validation failed. Check the request parameters.\",\"status\":400,\"data\":[],\"fieldErrors\":{\"adn\":\"DNA array must not be empty and all rows must have the same length\"}}";

        String requestBody = "{\n" +
                "    \"adn\": [\n" +
                "        \"ATGCGA\",\n" +
                "        \"CAGTGC\",\n" +
                "        \"TTATGT\",\n" +
                "        \"AGAAGG\",\n" +
                "        \"CCCCTA\",\n" +
                "        \"TCACT\"\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl +"/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.content().string(containsString(expectedResponse))));
    }

    @Test
    @Sql({"/data.sql"})
    public void mutantExistErrorNotFoundException () throws Exception {

        String expectedResponse = "{\"message\":\"This DNA sequence has already been used\",\"status\":404,\"data\":[],\"fieldErrors\":[]}";

        String requestBody = "{\n" +
                "    \"adn\": [\n" +
                "        \"ATGCGA\",\n" +
                "        \"CAGTGC\",\n" +
                "        \"TTATGT\",\n" +
                "        \"AGAAGG\",\n" +
                "        \"CCCCTA\",\n" +
                "        \"TCACTG\"\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl +"/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.content().string(containsString(expectedResponse))));
    }

    @Test
    public void isNotMutantErrorForbiddenException () throws Exception {

        String expectedResponse = "{\"message\":\"This sequence is not a mutant\",\"status\":403,\"data\":[],\"fieldErrors\":[]}";

        String requestBody = "{\n" +
                "    \"adn\": [\n" +
                "        \"ATCGTA\",\n" +
                "        \"GCTGAC\",\n" +
                "        \"TACGTT\",\n" +
                "        \"CGTACG\",\n" +
                "        \"AGCTGA\",\n" +
                "        \"TGATCG\"\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl +"/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isForbidden())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.content().string(containsString(expectedResponse))));
    }

    @Test
    public void isMutant () throws Exception {

        String expectedResponse = "{\"message\":\"This sequence is a mutant\",\"status\":200,\"data\":{\"adn\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]},\"fieldErrors\":[]}";

        String requestBody = "{\n" +
                "    \"adn\": [\n" +
                "        \"ATGCGA\",\n" +
                "        \"CAGTGC\",\n" +
                "        \"TTATGT\",\n" +
                "        \"AGAAGG\",\n" +
                "        \"CCCCTA\",\n" +
                "        \"TCACTG\"\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl +"/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.content().string(containsString(expectedResponse))));
    }

    @Test
    @Sql({"/data.sql"})
    public void mutantStatus () throws Exception {

        String expectedResponse = "{\"message\":\"This sequence is a mutant\",\"status\":200,\"data\":{\"countMutantDna\":1,\"countHumanDna\":0,\"ratio\":0.0},\"fieldErrors\":[]}";

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.content().string(containsString(expectedResponse))));
    }
}
