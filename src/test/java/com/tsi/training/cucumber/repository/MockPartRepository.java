package com.tsi.training.cucumber.repository;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.entity.Part;
import lombok.Getter;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MockPartRepository {

    private final Map<Long, PartDTO> partsInRepositoryByID;
    private final Map<String, PartDTO> partsInRepositoryByDescription;


    public MockPartRepository()
    {
        this.partsInRepositoryByID = new HashMap<>();
        this.partsInRepositoryByDescription = new HashMap<>();
    }


    public PartDTO save(PartDTO partDTO)
    {
        this.partsInRepositoryByID.put(partDTO.getId(), partDTO);
        this.partsInRepositoryByDescription.put(partDTO.getDescription(), partDTO);

        return partDTO;
    }


    public PartDTO findByID(Long id)
    {
        return this.partsInRepositoryByID.get(id);
    }

    public PartDTO findByDescription(String description)
    {
        return this.partsInRepositoryByDescription.get(description);
    }


    public PartDTO update(Long id, PartDTO newPartDTO)
    {
        delete(id);
        return save(newPartDTO);
    }


    public PartDTO delete(Long id)
    {
        PartDTO oldPartDTO = findByID(id);
        this.partsInRepositoryByID.remove(oldPartDTO.getId());
        this.partsInRepositoryByDescription.remove(oldPartDTO.getDescription());

        return oldPartDTO;
    }
}
