package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.request.PartRequest;

import java.util.List;

public interface IPartService {
    List<PartDTO> getAllParts();
    PartDTO getPartById(Long id);
    PartDTO createPart(PartRequest request);
    PartDTO updatePart(Long id, PartRequest request);
    void deletePart(Long id);
    PartDTO getPartByDescription(String description);
}
