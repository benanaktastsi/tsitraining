package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.entity.Part;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PartService implements IPartService {

    private final PartRepository partRepository;
    private final PartMapper partMapper;

    @Autowired
    public PartService(PartRepository partRepository, PartMapper partMapper) {
        this.partRepository = partRepository;
        this.partMapper = partMapper;
    }

    @Override
    public List<PartDTO> getAllParts() {
        List<Part> parts = partRepository.findAll();
        return partMapper.toDto(parts);
    }

    @Override
    public PartDTO getPartById(Long id) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Part not found"));
        return partMapper.toDto(part);
    }

    @Override
    public PartDTO createPart(PartDTO request) {
        Part part = partMapper.toEntity(request);
        Part createdPart = partRepository.save(part);
        return partMapper.toDto(createdPart);
    }

    @Override
    public PartDTO updatePart(Long id, PartDTO request) {
        Part existingPart = partRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Part not found"));
        partMapper.toEntity(request, existingPart);
        Part updatedPart = partRepository.save(existingPart);
        return partMapper.toDto(updatedPart);
    }

    @Override
    public void deletePart(Long id) {
        if (!partRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Part not found");
        }
        partRepository.deleteById(id);
    }

    @Override
    public PartDTO getPartByDescription(String description) {
        Part part = partRepository.findByDescription(description)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Part not found"));
        return partMapper.toDto(part);
    }
}
