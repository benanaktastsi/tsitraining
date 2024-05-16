package com.tsi.training.cucumber.stepdefs;

import com.tsi.training.cucumber.controller.MockPartControllerTest;
import com.tsi.training.dto.PartDTO;
import com.tsi.training.cucumber.dto.PartDTOInputOutputTest;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;
import java.util.stream.Collectors;


@RunWith(Cucumber.class)
@AutoConfigureMockMvc(printOnlyOnFailure = true)
public class PartsCRUDTestCase {

    private final MockPartControllerTest mockControllerTest;

    // private MvcResult mvcResult;
    private List<PartDTO> inputPartDTOList;
    private List<PartDTO> updatedPartDTOList;
    private List<PartDTO> resultPartDTOList;

    public PartsCRUDTestCase(MockMvc mockMvc)
    {
        this.mockControllerTest = new MockPartControllerTest(mockMvc);
    }


    // ==================================================
    // Convert Cucumber inputs to a wrapper object
    // ==================================================
    @DataTableType
    public PartDTOInputOutputTest convert(Map<String, String> entry)
    {
        return new PartDTOInputOutputTest(
                getPartDTOList("input", entry),
                getPartDTOList("output", entry));
        
    }

    private List<PartDTO> getPartDTOList(String inputOutput, Map<String, String> entry)
    {
        String[] idArray = !Objects.isNull(entry.get("id_" + inputOutput))
                ? entry.get("id_" + inputOutput).split(",")
                : new String[0];

        String[] descriptionArray = !Objects.isNull(entry.get("description_" + inputOutput))
                ? entry.get("description_" + inputOutput).split(",")
                : new String[0];

        String[] priceArray = !Objects.isNull(entry.get("price_" + inputOutput))
                ? entry.get("price_" + inputOutput).split(",")
                : new String[0];


        List<PartDTO> partDTOList = new LinkedList<>();
        for(int i = 0; i < descriptionArray.length; i++)
        {
            PartDTO newPartDTO = new PartDTO();
            // newPartDTO.setId(Long.valueOf(idArray[i]));
            newPartDTO.setDescription(descriptionArray[i]);
            newPartDTO.setPrice(Double.valueOf(priceArray[i]));

            partDTOList.add(newPartDTO);
        }
        return partDTOList;
    }


    // ==================================================
    // GIVEN steps
    // ==================================================

    /**
     * Used during Background
     */
    @Given("an empty Part Repository")
    public void givenEmptyPartRepository() throws Exception
    {
        this.mockControllerTest.sendNukeRequest();
    }

    /**
     * Used by Scenarios:
     * - CREATE a Part
     * - READ from Part Repository
     * - UPDATE a Part
     * - DELETE a Part
     */
    @Given("an existing Part Repository with")
    public void givenAnExistingPartRepository(PartDTOInputOutputTest partDTOInputOutputTest) throws Exception
    {

        List<PartDTO> partDTOList = partDTOInputOutputTest.getInputPartDTOList();
        for(PartDTO partDTO : partDTOList) this.mockControllerTest.sendPostRequestSavePart(partDTO);
    }

    /**
     * Used by Scenarios:
     * - CREATE a Part
     * - READ from Part Repository
     * - UPDATE a Part
     * - DELETE a Part
     */
    @Given("a Part DTO with")
    public void givenPartDTO(PartDTOInputOutputTest partDTOInputOutputTest)
    {
        this.inputPartDTOList = partDTOInputOutputTest.getInputPartDTOList();
    }

    /**
     * Used by Scenarios:
     * - UPDATE a Part
     */
    @Given("an updated Part DTO with")
    public void givenUpdatedPartDTO(PartDTOInputOutputTest partDTOInputOutputTest)
    {
        this.updatedPartDTOList = new LinkedList<>();
        this.updatedPartDTOList.addAll(partDTOInputOutputTest.getInputPartDTOList());
    }


    // ==================================================
    // WHEN steps
    // ==================================================

    /**
     * Used by Scenarios:
     * - CREATE a Part
     */
    @When("save Part to Part Repository")
    public void whenSavePartToPartRepository() throws Exception
    {
        for(PartDTO inputPartDTO : this.inputPartDTOList)
        {
            this.mockControllerTest.sendPostRequestSavePart(inputPartDTO);
        }
    }

    /**
     * Used by Scenarios:
     * - READ from Part Repository
     */
    @When("find Part from Part Repository by description")
    public void whenFindPartFromPartRepositoryByDescription() throws Exception
    {
        this.resultPartDTOList = new LinkedList<>();
        for(PartDTO inputPartDTO : this.inputPartDTOList) {
            PartDTO returnedDTO = this.mockControllerTest.sendGetRequestFindPartByDescription(inputPartDTO);
            if(null != returnedDTO) this.resultPartDTOList.add(returnedDTO);
        }
    }

    /**
     * Used by Scenarios:
     * - UPDATE a Part
     */
    @When("update Part from updated Part DTO")
    public void whenUpdatePartFromUpdatedPartDTO() throws Exception
    {
        for(int i = 0; i < this.inputPartDTOList.size(); i++)
        {
            // Get the ID stored in the database (since they're auto-generated)
            PartDTO originalDTO = this.mockControllerTest.sendGetRequestFindPartByDescription(this.inputPartDTOList.get(i));
            PartDTO updatedDTO = this.updatedPartDTOList.get(i);

            if(null != originalDTO) this.inputPartDTOList.set(i, originalDTO);
            updatedDTO.setId(this.inputPartDTOList.get(i).getId());


            // Send PATCH request to update
            this.mockControllerTest.sendPatchRequestUpdatePart(
                    this.inputPartDTOList.get(i),
                    updatedDTO);
        }
    }

    /**
     * Used by Scenarios:
     * - DELETE a Part
     */
    @When("delete Part from Part Repository")
    public void whenDeletePartFromPartRepository() throws Exception {
        for (int i = 0; i < this.inputPartDTOList.size(); i++) {
            // Get the ID stored in the database (since they're auto-generated)
            PartDTO originalDTO = this.mockControllerTest.sendGetRequestFindPartByDescription(this.inputPartDTOList.get(i));

            if (null != originalDTO) this.inputPartDTOList.set(i, originalDTO);


            // Send DELETE request to delete
            this.mockControllerTest.sendDeleteRequestDeletePart(this.inputPartDTOList.get(i));

        }
    }


    // ==================================================
    // THEN steps
    // ==================================================

    /**
     * Used by Scenarios:
     * - CREATE a Part
     * - UPDATE a Part
     * - DELETE a Part
     */
    @Then("expect Part Repository with")
    public void thenExpectPartRepository(PartDTOInputOutputTest expectedPartDTOInputOutputTest) throws Exception
    {
        // Sort the lists by description before asserting
        // Set IDs to null for assertion as they're auto-generated by database
        List<PartDTO> responsePartDTOList = this.mockControllerTest.sendGetRequestFindAllParts()
                .stream()
                .sorted(Comparator.comparing(PartDTO :: getDescription).reversed())
                .collect(Collectors.toList());

        List<PartDTO> expectedPartDTOList = expectedPartDTOInputOutputTest.getOutputPartDTOList()
                .stream()
                .sorted(Comparator.comparing(PartDTO :: getDescription).reversed())
                .collect(Collectors.toList());

        responsePartDTOList.forEach(partDTO -> partDTO.setId(null));
        expectedPartDTOList.forEach(partDTO -> partDTO.setId(null));

        Assert.assertEquals(expectedPartDTOList, responsePartDTOList);

    }

    /**
     * Used by Scenarios:
     * - READ from Part Repository
     */
    @Then("expect Part returned with")
    public void thenExpectPartReturned(PartDTOInputOutputTest expectedPartDTOInputOutputTest)
    {
        List<PartDTO> expectedPartDTOList = expectedPartDTOInputOutputTest.getOutputPartDTOList()
                .stream()
                .sorted(Comparator.comparing(PartDTO :: getId).reversed())
                .collect(Collectors.toList());

        expectedPartDTOList.forEach(partDTO -> partDTO.setId(null));
        this.resultPartDTOList.forEach(partDTO -> partDTO.setId(null));

        Assert.assertEquals(expectedPartDTOList, this.resultPartDTOList);
    }
}
