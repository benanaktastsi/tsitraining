package com.tsi.training.controller;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.repository.PartRepository;
import com.tsi.training.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for handling Part operations.
 */
@RestController
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;

    /**
     * Constructor injection for PartService.
     *
     * @param partService Part service
     */
    public PartController(PartService partService) {
        this.partService = partService;
    }

    /**
     * Get all parts.
     *
     * @return ResponseEntity containing a list of PartDTOs
     */
    @GetMapping
    public ResponseEntity<List<PartDTO>> getAllParts() {
        try {
            List<PartDTO> parts = partService.getAllParts();
            return ResponseEntity.ok(parts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get a part by its ID.
     *
     * @param id Part ID
     * @return ResponseEntity containing the PartDTO if found, otherwise not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<PartDTO> getPartById(@PathVariable Long id) {
        try {
            Optional<PartDTO> partOptional = Optional.ofNullable(partService.getPartById(id));
            return partOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create a new part.
     *
     * @param request Part request
     * @return ResponseEntity containing the created PartDTO
     */
    @PostMapping
    public ResponseEntity<PartDTO> createNewPart(@Validated @RequestBody PartDTO request) {
        try {
            PartDTO createdPart = partService.createPart(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an existing part.
     *
     * @param id      Part ID
     * @param request Part request
     * @return ResponseEntity containing the updated PartDTO if found, otherwise not found
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PartDTO> updatePart(@PathVariable Long id, @RequestBody PartDTO request) {
        try {
            Optional<PartDTO> updatedPartOptional = Optional.ofNullable(partService.updatePart(id, request));
            return updatedPartOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a part by its ID.
     *
     * @param id Part ID
     * @return ResponseEntity indicating success or failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
        try {
            partService.deletePart(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get a part by its description.
     *
     * @param description Part description
     * @return ResponseEntity containing the PartDTO if found, otherwise not found
     */
    @GetMapping("/description/{description}")
    public ResponseEntity<PartDTO> getPartByDescription(@PathVariable String description) {
        try {
            Optional<PartDTO> partOptional = Optional.ofNullable(partService.getPartByDescription(description));
            return partOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * REMOVE LATER - USED TO RESET REPOSITORY DURING TESTING
     */

    @DeleteMapping("/nuke")
    public void deleteAllEntries()
    {
        this.partService.deleteAllEntries();
    }

}
