package com.tsi.training.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class PartDTOInputOutputTest {

    private List<PartDTO> inputPartDTOList;
    private List<PartDTO> outputPartDTOList;

    public PartDTOInputOutputTest(List<PartDTO> inputPartDTOList, List<PartDTO> outputPartDTOList)
    {
        this.inputPartDTOList = inputPartDTOList;
        this.outputPartDTOList = outputPartDTOList;
    }

    public List<PartDTO> getInputPartDTOList()
    {
        return this.inputPartDTOList;
    }

    public List<PartDTO> getOutputPartDTOList()
    {
        return this.outputPartDTOList;
    }
}
