package com.tsi.training.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StartMessageRequest {
    @NotNull
    private String messageText;
}
