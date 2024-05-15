package com.tsi.training.cucumber;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.dto.PartDTOInputOutputTest;
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
public class PartsCRUDTests {

    private final MockControllerTest mockControllerTest;

    // private MvcResult mvcResult;
    private List<PartDTO> inputPartDTOList;
    private List<PartDTO> updatedPartDTOList;
    private List<PartDTO> resultPartDTOList;

    public PartsCRUDTests(MockMvc mockMvc)
    {
        this.mockControllerTest = new MockControllerTest(mockMvc);
    }



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
        for(int i = 0; i < idArray.length; i++)
        {
            PartDTO newPartDTO = new PartDTO();
            newPartDTO.setId(Long.valueOf(idArray[i]));
            newPartDTO.setDescription(descriptionArray[i]);
            newPartDTO.setPrice(Double.valueOf(priceArray[i]));

            partDTOList.add(newPartDTO);
        }

        return partDTOList;
    }

    // ==================================================
    // ==================================================


    @Given("an existing Part Repository with")
    public void givenAnExistingPartRepository(PartDTOInputOutputTest partDTOInputOutputTest) throws Exception
    {
        this.mockControllerTest.sendNukeRequest();

        List<PartDTO> partDTOList = partDTOInputOutputTest.getInputPartDTOList();
        for(PartDTO partDTO : partDTOList) this.mockControllerTest.sendPostRequestSavePart(partDTO);
    }


    @Given("a Part DTO with")
    public void givenPartDTO(PartDTOInputOutputTest partDTOInputOutputTest)
    {
        this.inputPartDTOList = partDTOInputOutputTest.getInputPartDTOList();
    }

    @When("save Part to Part Repository")
    public void whenSavePartToPartRepository() throws Exception
    {
        for(PartDTO inputPartDTO : this.inputPartDTOList)
        {
            this.mockControllerTest.sendPostRequestSavePart(inputPartDTO);
        }
    }

    @Then("expect Part Repository with")
    public void thenExpectPartRepository(PartDTOInputOutputTest expectedPartDTOInputOutputTest) throws Exception
    {
        // Sort the lists by ID before asserting
        List<PartDTO> responsePartDTOList = this.mockControllerTest.sendGetRequestFindAllParts()
                .stream()
                .sorted(Comparator.comparing(PartDTO :: getId).reversed())
                .collect(Collectors.toList());

        List<PartDTO> expectedPartDTOList = expectedPartDTOInputOutputTest.getOutputPartDTOList()
                .stream()
                .sorted(Comparator.comparing(PartDTO :: getId).reversed())
                .collect(Collectors.toList());;


        Assert.assertEquals(expectedPartDTOList, responsePartDTOList);

    }


    // ==================================================



    @When("find Part from Part Repository by description")
    public void whenFindPartFromPartRepositoryByDescription() throws Exception
    {
        this.resultPartDTOList = new LinkedList<>();
        for(PartDTO inputPartDTO : this.inputPartDTOList) {
            this.resultPartDTOList
                    .add(this.mockControllerTest.sendGetRequestFindPartByDescription(inputPartDTO));
        }
    }

    @Then("expect Part returned with")
    public void thenExpectPartReturned(PartDTOInputOutputTest expectedPartDTOInputOutputTest)
    {
        List<PartDTO> expectedPartDTOList = expectedPartDTOInputOutputTest.getOutputPartDTOList()
                .stream()
                .sorted(Comparator.comparing(PartDTO :: getId).reversed())
                .collect(Collectors.toList());;

        Assert.assertEquals(expectedPartDTOList, this.resultPartDTOList);
    }



    // ==================================================



    @Given("an updated Part DTO with")
    public void givenUpdatedPartDTO(PartDTOInputOutputTest partDTOInputOutputTest)
    {
        this.updatedPartDTOList = new LinkedList<>();
        this.updatedPartDTOList.addAll(partDTOInputOutputTest.getInputPartDTOList());
    }

    @When("update Part from updated Part DTO")
    public void whenUpdatePartFromUpdatedPartDTO() throws Exception
    {
        for(int i = 0; i < this.inputPartDTOList.size(); i++)
        {
            this.mockControllerTest.sendPatchRequestUpdatePart(
                    this.inputPartDTOList.get(i),
                    this.updatedPartDTOList.get(i));
        }
    }


    // ==================================================


    @When("delete Part from Part Repository")
    public void whenDeletePartFromPartRepository() throws Exception
    {
        for(PartDTO inputPartDTO : this.inputPartDTOList) this.mockControllerTest.sendDeleteRequestDeletePart(inputPartDTO);
    }







}
