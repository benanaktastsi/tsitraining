package com.tsi.training.controller;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.service.PartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PartControllerTest {

    private PartService partServiceMock;
    private PartController partController;
    private PartDTO part;

    @Before
    public void setup() {
        partServiceMock = mock(PartService.class);
        partController = new PartController(partServiceMock);

        part = new PartDTO(1L, "Engine", 99.99);
    }

    @Test
    public void getAllPartsTest() {
        // Arrange
        PartDTO part2 = new PartDTO(1L, "Wheel", 10.99);
        List<PartDTO> parts = new ArrayList<>(Arrays.asList(part, part2));

        when(partServiceMock.getAllParts()).thenReturn(parts);

        // Act
        ResponseEntity<List<PartDTO>> response = partController.getAllParts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getPartByIdTest() {
        // Arrange
        when(partServiceMock.getPartById(1L)).thenReturn(part);

        // Act
        ResponseEntity<PartDTO> response = partController.getPartById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(part, response.getBody());
    }

    @Test
    public void createNewPartTest() {
        // Arrange
        PartDTO input = new PartDTO(null, "Engine", 99.99);
        when(partServiceMock.createPart(input)).thenReturn(part);

        // Act
        var response = partController.createNewPart(input);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(part, response.getBody());
    }

    @Test
    public void updatePartTest() {
        // Arrange
        PartDTO input = new PartDTO(null, "Engine", 99.99);
        when(partServiceMock.updatePart(1L, input)).thenReturn(part);

        // Act
        ResponseEntity<PartDTO> response = partController.updatePart(1L, input);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(part, response.getBody());
    }

    @Test
    public void deletePartTest() {
        // Act
        var response = partController.deletePart(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void getPartByDescriptionTest() {
        // Arrange
        when(partServiceMock.getPartByDescription("Engine")).thenReturn(part);

        // Act
        ResponseEntity<PartDTO> response = partController.getPartByDescription("Engine");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(part, response.getBody());
    }
}
