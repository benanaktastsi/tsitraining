package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;

import java.util.List;

public interface IPartService {
    List<PartDTO> getAllParts();
    PartDTO getPartById(Long id);
    PartDTO createPart(PartDTO request);
    PartDTO updatePart(Long id, PartDTO request);
    void deletePart(Long id);
    PartDTO getPartByDescription(String description);
}
