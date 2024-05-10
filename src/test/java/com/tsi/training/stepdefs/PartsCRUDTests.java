package com.tsi.training.stepdefs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tsi.training.dto.PartDTO;
import com.tsi.training.util.MockControllerTest;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(Cucumber.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class PartsCRUDTests {

    private MockControllerTest mockControllerTest;

    // private MvcResult mvcResult;
    private PartDTO inputPartDTO;
    private PartDTO updatedPartDTO;
    private PartDTO resultPartDTO;



    @DataTableType
    public PartDTO convert(Map<String, String> entry)
    {
        PartDTO partDTO = new PartDTO();
        partDTO.setId(Long.valueOf(entry.get("id")));
        partDTO.setDescription(entry.get("description"));
        partDTO.setPrice(Double.valueOf(entry.get("price")));

        return partDTO;
    }

    // ==================================================
    // ==================================================


    @Given("a Part DTO with")
    public void givenPartDTO(PartDTO partDTO)
    {
        this.inputPartDTO = partDTO;
    }

    @When("save Part to Part Repository")
    public void whenSavePartToPartRepository() throws Exception
    {
        this.mockControllerTest.sendPostRequestSavePart(this.inputPartDTO);
    }

    @Then("expect Part Repository with")
    public void thenExpectPartRepository(List<PartDTO> expectedPartDTOList) throws Exception
    {
        List<PartDTO> responsePartDTOList = this.mockControllerTest.sendGetRequestFindAllParts();

        for(int i = 0; i < expectedPartDTOList.size(); i++)
        {
            PartDTO expectedDTO = expectedPartDTOList.get(i);
            PartDTO responseDTO = responsePartDTOList.get(i);

            Assert.assertEquals(expectedDTO, responseDTO);
        }
    }


    // ==================================================



    @Given("an existing Part Repository with")
    public void givenAnExistingPartRepository(List<PartDTO> partDTOList) throws Exception
    {
        for(PartDTO partDTO : partDTOList) this.mockControllerTest.sendPostRequestSavePart(partDTO);
    }

    @When("find Part from Part Repository by description")
    public void whenFindPartFromPartRepositoryByDescription() throws Exception
    {
        this.resultPartDTO = this.mockControllerTest.sendGetRequestFindPartByDescription(this.inputPartDTO);
    }

    @Then("expect Part returned with")
    public void thenExpectPartReturned(PartDTO expectedPartDTO)
    {
        Assert.assertEquals(expectedPartDTO, this.resultPartDTO);
    }



    // ==================================================



    @Given("an updated Part DTO with")
    public void givenUpdatedPartDTO(PartDTO updatedPartDTO)
    {
        this.updatedPartDTO = updatedPartDTO;
    }

    @When("update Part from updated Part DTO")
    public void whenUpdatePartFromUpdatedPartDTO() throws Exception
    {
        this.mockControllerTest.sendPatchRequestUpdatePart(this.inputPartDTO, this.updatedPartDTO);
    }


    // ==================================================


    @When("delete Part from Part Repository")
    public void whenDeletePartFromPartRepository() throws Exception
    {
        this.mockControllerTest.sendDeleteRequestDeletePart(this.inputPartDTO);
    }







}
