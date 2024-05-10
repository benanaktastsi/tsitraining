package com.tsi.training.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsi.training.dto.PartDTO;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(Cucumber.class)
@AutoConfigureMockMvc
public class MockControllerTest {

    @Autowired private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public void sendPostRequestSavePart(PartDTO partDTO) throws Exception
    {
        this.mockMvc
                .perform(post("/api/parts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper
                                .writeValueAsString(partDTO)))
        // .andDo(print())
        // .andReturn()
        ;


    }


    public PartDTO sendGetRequestFindPartByDescription(PartDTO partDTO) throws Exception
    {
        return this.objectMapper.readValue(this.mockMvc
                .perform(get("/api/parts/description/{description}", partDTO.getDescription()))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString(),

                PartDTO.class);
    }

    public List<PartDTO> sendGetRequestFindAllParts() throws Exception
    {
        return this.objectMapper.readValue(this.mockMvc
                .perform(get("/api/parts"))
                .andReturn()
                .getResponse()
                .getContentAsString(),

                new TypeReference<List<PartDTO>>(){});
    }


    public void sendPatchRequestUpdatePart(PartDTO originalDTO, PartDTO updatedDTO) throws Exception
    {
        this.mockMvc
                .perform(patch("/api/parts/{id}", originalDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .writeValueAsString(updatedDTO)))
                .andDo(print());

    }


    public void sendDeleteRequestDeletePart(PartDTO partDTO) throws Exception
    {
        this.mockMvc
                .perform(delete("/api/parts/{id}", partDTO.getId()));
    }
}
