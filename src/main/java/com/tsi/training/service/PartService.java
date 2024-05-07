package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.entity.Part;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.repository.PartRepository;
import com.tsi.training.request.PartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PartService implements IPartService {
    @Autowired
    PartRepository partRepository;

    @Autowired
    PartMapper mapper;

    public List<PartDTO> getAllParts() {
        List<Part> parts = partRepository.findAll();

        return mapper.toDto(parts);
    }

    public PartDTO getPartById(Long id) {
        Optional<Part> part = partRepository.findById(id);

        if (part.isPresent()) {
            return mapper.toDto(part.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Part with id %d does not exist.", id));
        }
    }

    public PartDTO createPart(PartRequest request) {
        final Part part = new Part(request);
        final Part createdPart = partRepository.save(part);

        return mapper.toDto(createdPart);
    }

    public PartDTO updatePart(Long id, PartRequest request) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Part with id %d does not exist.", id)));

        part.setDescription(request.description);
        part.setPrice(request.price);

        final Part updatedPart = partRepository.save(part);
        return mapper.toDto(updatedPart);
    }

    public void deletePart(Long id) {
        final boolean partExists = partRepository.existsById(id);
        if (partExists) {
            partRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Part with id %d does not exist.", id));
        }
    }

    public PartDTO getPartByDescription(String description) {
        Optional<Part> part = partRepository.findByDescription(description);
        if (part.isPresent()) {
            return mapper.toDto(part.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Part with description %s does not exist.", description));
        }
    }
}
