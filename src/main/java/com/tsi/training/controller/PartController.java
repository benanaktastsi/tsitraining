package com.tsi.training.controller;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.request.PartRequest;
import com.tsi.training.service.IPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PartController {

    @Autowired
    IPartService partService;

    @GetMapping("/parts")
    public ResponseEntity<List<PartDTO>> getAllParts() {
        final var parts = partService.getAllParts();

        return ResponseEntity.ok(parts);
    }

    @GetMapping("/parts/{id}")
    public ResponseEntity<PartDTO> getPartById(@PathVariable Long id) {
        final var part = partService.getPartById(id);

        return ResponseEntity.ok(part);
    }

    @PostMapping("/parts")
    public ResponseEntity<PartDTO> createPart(@Validated @RequestBody PartRequest request) {
        final var part = partService.createPart(request);

        return ResponseEntity.ok(part);
    }

    @PatchMapping("/parts/{id}")
    public ResponseEntity<PartDTO> updatePart(@PathVariable Long id, @RequestBody PartRequest request) {
        final var updatedPart = partService.updatePart(id, request);

        return ResponseEntity.ok(updatedPart);
    }

    @DeleteMapping("/parts/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
        partService.deletePart(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/parts/{description}")
    public ResponseEntity<PartDTO> getPartByDescription(@PathVariable String description) {
        final var part = partService.getPartByDescription(description);

        return ResponseEntity.ok(part);
    }
}
