package com.tsi.training.cucumber.mapper;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.entity.Part;

public class MockPartMapper {

    public PartDTO toDTO(Part part)
    {
        PartDTO partDTO = new PartDTO();
        partDTO.setId(part.getId());
        partDTO.setDescription(part.getDescription());
        partDTO.setPrice(part.getPrice());

        return partDTO;
    }

    public Part toEntity(PartDTO partDTO)
    {
        Part part = new Part();
        part.setId(partDTO.getId());
        part.setDescription(partDTO.getDescription());
        part.setPrice(partDTO.getPrice());

        return part;
    }
}
