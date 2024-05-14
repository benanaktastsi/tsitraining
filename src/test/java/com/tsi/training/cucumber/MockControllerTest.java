package com.tsi.training.cucumber;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsi.training.dto.PartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
public class MockControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MockControllerTest(MockMvc mockMvc)
    {
        this.mockMvc = mockMvc;
    }

    public PartDTO sendPostRequestSavePart(PartDTO partDTO)
    {
       try
       {
           return this.objectMapper.readValue(this.mockMvc
                           .perform(post("/api/parts")
                                   .contentType(MediaType.APPLICATION_JSON)
                                   .content(this.objectMapper
                                           .writeValueAsString(partDTO)))
                           .andReturn()
                           .getResponse()
                           .getContentAsString(),

                   PartDTO.class);
       }
       catch(Exception e)
       {
           log.warn("Failed to complete POST request to save Part");
           return null;
       }


    }


    public PartDTO sendGetRequestFindPartByDescription(PartDTO partDTO) throws Exception
    {
        try
        {
            return this.objectMapper.readValue(this.mockMvc
                            .perform(get("/api/parts/description/{description}", partDTO.getDescription()))
                            .andReturn()
                            .getResponse()
                            .getContentAsString(),

                    PartDTO.class);
        }
        catch(Exception e)
        {
            log.warn("Failed to complete GET request to find Part by description.");
            return null;
        }
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


    public PartDTO sendPatchRequestUpdatePart(PartDTO originalDTO, PartDTO updatedDTO) throws Exception
    {
        try {
            return this.objectMapper.readValue(this.mockMvc
                            .perform(patch("/api/parts/{id}", originalDTO.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(new ObjectMapper()
                                            .writeValueAsString(updatedDTO)))
                            .andReturn()
                            .getResponse()
                            .getContentAsString(),

                    PartDTO.class);
        }
        catch(Exception e)
        {
            log.warn("Failed to complete PATCH request to update Part");
            return null;
        }
    }


    public void sendDeleteRequestDeletePart(PartDTO partDTO) throws Exception
    {
        this.mockMvc
                .perform(delete("/api/parts/{id}", partDTO.getId()));
    }

    public void sendNukeRequest() throws Exception
    {
        this.mockMvc.perform(delete("/api/parts/nuke"));
    }
}
