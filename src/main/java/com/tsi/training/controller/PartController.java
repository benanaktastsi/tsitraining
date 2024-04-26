package com.tsi.training.controller;

import com.tsi.training.entity.Part;
import com.tsi.training.service.IPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PartController {

    @Autowired
    IPartService partService;

    public PartController(IPartService partService) {
        this.partService = partService;
    }

    @GetMapping("/parts/{description}")
    public ResponseEntity<Part> getPartByDescription(@PathVariable String description) {
        final var part = partService.getPartByDescription(description);
        if (part.isPresent()) {
            return ResponseEntity.ok(part.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
