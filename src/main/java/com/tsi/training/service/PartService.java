package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.dto.response.OrderDTO;
import com.tsi.training.entity.Part;
import com.tsi.training.exception.NoPartExistsException;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.repository.PartRepository;
import com.tsi.training.util.ProcessResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class PartService  {

    private final PartRepository partRepository;
    private final PartMapper partMapper;


    public List<PartDTO> getAllParts() {
        List<Part> parts = partRepository.findAll();
        return partMapper.toDto(parts);
    }

    public PartDTO getPartById(Long id) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new NoPartExistsException("Part not found with id " + id));
        return partMapper.toDto(part);
    }

    public PartDTO createPart(PartDTO request) {
        Part part = partMapper.toEntity(request);
        Part createdPart = partRepository.save(part);
        return partMapper.toDto(createdPart);
    }

    public PartDTO updatePart(Long id, PartDTO request) {
        Part existingPart = partRepository.findById(id)
                .orElseThrow(() -> new NoPartExistsException("Part not found with id " + id));
        partMapper.toEntity(request, existingPart);
        Part updatedPart = partRepository.save(existingPart);
        return partMapper.toDto(updatedPart);
    }

    public void deletePart(Long id) {
        if (!partRepository.existsById(id)) {
            throw new NoPartExistsException("Part not found with id " + id);
        }
        partRepository.deleteById(id);
    }

    public PartDTO getPartByDescription(String description) {
        Part part = partRepository.findByDescription(description)
                .orElseThrow(() -> new NoPartExistsException("Part not found with this description: " + description));
        return partMapper.toDto(part);
    }

    public void validateParts(ProcessResponse response) {
        List<Part> parts = partRepository.findByDescriptionIn(response.getParts());

        // Extract unique part descriptions
        Set<String> descriptions = new HashSet<>();
        for (Part part : parts) {
            descriptions.add(part.getDescription());
        }

        removePartsIfNotExist(response, descriptions);
    }

    // Remove any parts from a list of orders that are not in the database
    // TODO: Logging
    private void removePartsIfNotExist(ProcessResponse response, Set<String> descriptions) {
        for (OrderDTO order : response.getOrders()) {
            order.getParts().removeIf(part -> !descriptions.contains(part.getPartDescription()));

            // If an order no longer has any parts, remove the order
            if (order.getParts().isEmpty()) {
                response.getOrders().remove(order);
            }
        }
    }
}
