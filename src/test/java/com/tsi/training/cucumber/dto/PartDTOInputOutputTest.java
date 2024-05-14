package com.tsi.training.cucumber.dto;


import com.tsi.training.dto.PartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
public class PartDTOInputOutputTest {

    private final List<PartDTO> inputPartDTOList;
    private final List<PartDTO> outputPartDTOList;

    public PartDTOInputOutputTest(List<PartDTO> inputPartDTOList, List<PartDTO> outputPartDTOList)
    {
        this.inputPartDTOList = inputPartDTOList;
        this.outputPartDTOList = outputPartDTOList;
    }

}
