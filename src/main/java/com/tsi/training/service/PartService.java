package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.dto.response.OrderItemDTO;
import com.tsi.training.entity.Part;
import com.tsi.training.exception.NoOrderExistsException;
import com.tsi.training.exception.NoPartExistsException;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.repository.PartRepository;
import com.tsi.training.util.ProcessResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        if (CollectionUtils.isEmpty(response.getOrders())) {
            throw new NoOrderExistsException("No orders present in input.");
        }

        List<String> descriptions = partRepository.findByDescriptionIn(response.getParts());
        if (CollectionUtils.isEmpty(descriptions)) {
            throw new NoPartExistsException("No part descriptions found in database.");
        }

        // Remove parts from order if not existing in database
        response.getOrders().forEach(order -> {
            List<String> partsToRemove = order.getParts().stream()
                    .filter(part -> !descriptions.contains(part.getPartDescription()))
                    .peek(part -> log.warn("Removing part: {} from order with reference: {}",
                            part.getPartDescription(), order.getOrderReference()))
                    .map(OrderItemDTO::getPartDescription)
                    .collect(Collectors.toList());

            order.getParts().removeIf(part -> partsToRemove.contains(part.getPartDescription()));
        });


        // If an order no longer has any parts, remove the order
        response.getOrders().removeIf(order -> order.getParts().isEmpty());
    }




    /**
     * REMOVE LATER - USED TO RESET REPOSITORY DURING CUCUMBER TESTING
     */
    public void deleteAllEntries()
    {
        this.partRepository.deleteAll();
    }
}
