package com.tsi.training.service;

import com.tsi.training.entity.Part;

import java.util.Optional;

public interface IPartService {
    Optional<Part> getPartByDescription(String description);
}
