package com.tsi.training.service;

import com.tsi.training.entity.Part;
import com.tsi.training.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartService implements IPartService {

    @Autowired
    PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Optional<Part> getPartByDescription(String description) {
        return partRepository.findByDescription(description);
    }
}
