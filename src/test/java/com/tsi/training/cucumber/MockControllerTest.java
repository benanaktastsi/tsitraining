package com.tsi.training.cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsi.training.dto.PartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.HttpURLConnection;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Slf4j
public class MockControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MockControllerTest(MockMvc mockMvc)
    {
        this.mockMvc = mockMvc;
    }


    public PartDTO sendPostRequestSavePart(PartDTO partDTO) throws Exception
    {
       MvcResult mvcResult = this.mockMvc
               .perform(post("/api/parts")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(this.objectMapper
                               .writeValueAsString(partDTO)))
               .andReturn();

       switch(mvcResult.getResponse().getStatus())
       {
           case HttpURLConnection.HTTP_BAD_REQUEST:
               log.warn("ERROR {} - Failed to complete POST request to save Part", HttpURLConnection.HTTP_BAD_REQUEST);
               return null;

           case HttpURLConnection.HTTP_CREATED:
               return this.objectMapper.readValue(mvcResult
                               .getResponse()
                               .getContentAsString(),

                       PartDTO.class);
       }

        log.warn("Unknown Response Status {}", mvcResult.getResponse().getStatus());
        return null;
    }


    public PartDTO sendGetRequestFindPartByDescription(PartDTO partDTO) throws Exception
    {
        MvcResult mvcResult = this.mockMvc
                .perform(get("/api/parts/description/{description}", partDTO.getDescription()))
                .andReturn();

        switch(mvcResult.getResponse().getStatus())
        {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                log.warn("ERROR {} - Failed to complete GET request to find Part by description", HttpURLConnection.HTTP_BAD_REQUEST);
                return null;

            case HttpURLConnection.HTTP_OK:
                return this.objectMapper.readValue(mvcResult
                                .getResponse()
                                .getContentAsString(),

                        PartDTO.class);
        }

        log.warn("Unknown Response Status {}", mvcResult.getResponse().getStatus());
        return null;
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
        MvcResult mvcResult = this.mockMvc
                .perform(patch("/api/parts/{id}", originalDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .writeValueAsString(updatedDTO)))
                .andReturn();

        switch (mvcResult.getResponse().getStatus())
        {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                log.warn("ERROR {} - Failed to complete PATCH request to update Part", HttpURLConnection.HTTP_BAD_REQUEST);
                return null;

            case HttpURLConnection.HTTP_OK:
                return this.objectMapper.readValue(mvcResult
                                .getResponse()
                                .getContentAsString(),

                        PartDTO.class);
        }

        log.warn("Unknown Response Status {}", mvcResult.getResponse().getStatus());
        return null;
    }


    public void sendDeleteRequestDeletePart(PartDTO partDTO) throws Exception
    {
        this.mockMvc.perform(delete("/api/parts/{id}", partDTO.getId()));
    }

    public void sendNukeRequest() throws Exception
    {
        this.mockMvc.perform(delete("/api/parts/nuke"));
    }
}
