package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.dto.response.OrderDTO;
import com.tsi.training.dto.response.OrderItemDTO;
import com.tsi.training.entity.Part;
import com.tsi.training.exception.NoPartExistsException;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.repository.PartRepository;
import com.tsi.training.util.ProcessResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
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
        List<String> descriptions = partRepository.findByDescriptionIn(response.getParts());

        // Remove parts from order if not existing in database
        response.getParts().forEach(order -> {
            List<String> removedParts = response.getParts().stream()
                    .filter(part -> !descriptions.contains(part))
                    .peek(part -> log.warn("Removing part: {}", part))
                    .collect(Collectors.toList());
            response.getParts().removeAll(removedParts);
        });

        // If an order no longer has any parts, remove the order
        response.getOrders().removeIf(order -> order.getParts().isEmpty());
    }
}
