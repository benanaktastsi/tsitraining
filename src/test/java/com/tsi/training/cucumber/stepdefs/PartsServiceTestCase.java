package com.tsi.training.cucumber.stepdefs;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.entity.Part;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.repository.PartRepository;
import com.tsi.training.service.PartService;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

@RunWith(Cucumber.class)
public class PartsServiceTestCase {

    private final PartService partService;
    private final PartRepository partRepository;
    private final PartMapper partMapper;

    private final List<PartDTO> partsInRepository;
    private PartDTO inputPartDTO;
    private PartDTO resultPartDTO;

    public PartsServiceTestCase()
    {
        this.partRepository = Mockito.mock(PartRepository.class);
        this.partMapper = Mockito.mock(PartMapper.class);
        this.partService = new PartService(this.partRepository, this.partMapper);

        this.partsInRepository = new LinkedList<>();
    }


    @DataTableType
    public PartDTO convert(Map<String, String> entry)
    {
        PartDTO partDTO = new PartDTO();
        partDTO.setId(Long.valueOf(entry.get("id")));
        partDTO.setDescription(entry.get("description"));
        partDTO.setPrice(Double.valueOf(entry.get("price")));

        System.out.println(partDTO.getDescription());
        return partDTO;
    }




    @Given("an existing Part Repository with \\(PartsService.feature)")
    public void givenExistingPartRepository(List<PartDTO> partDTOList) throws InstantiationException, IllegalAccessException {

        for(PartDTO partDTO : partDTOList)
        {
            Mockito.when(this.partMapper.toEntity(partDTO))
                    .thenReturn(new Part());
            Mockito.when(this.partRepository.save(Mockito.any(Part.class)))
                    .thenAnswer(answer -> answer.getArgument(0));
            Mockito.when(this.partMapper.toDto(Mockito.any(Part.class)))
                    .thenReturn(partDTO);

            this.partsInRepository.add(
                    this.partService.createPart(partDTO));
        }

        System.out.println(this.partsInRepository);
    }


    @Given("a PartDTO with \\(PartsService.feature)")
    public void givenPartDTO(PartDTO partDTO)
    {
        this.inputPartDTO = partDTO;
    }

    @When("create Part \\(PartsService.feature)")
    public void WhenCreatePart()
    {
        Mockito.when(this.partMapper.toEntity(this.inputPartDTO))
                .thenReturn(new Part());
        Mockito.when(this.partRepository.save(Mockito.any(Part.class)))
                .thenAnswer(answer -> answer.getArgument(0));
        Mockito.when(this.partMapper.toDto(Mockito.any(Part.class)))
                .thenReturn(this.inputPartDTO);

        this.resultPartDTO = this.partService.createPart(this.inputPartDTO);
        this.partsInRepository.add(this.resultPartDTO);
    }

    @Then("expect returned PartDTO with \\(PartsService.feature)")
    public void thenExpectReturnedPartDTO(PartDTO expectedPartDTO)
    {
        Assert.assertEquals(expectedPartDTO, this.resultPartDTO);
    }

    @Then("expect a Part Repository with \\(PartsService.feature)")
    public void thenExpectPartRepository(List<PartDTO> expectedPartDTOList)
    {
        Assert.assertEquals(expectedPartDTOList, this.partsInRepository);
    }
}
