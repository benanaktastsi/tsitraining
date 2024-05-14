package com.tsi.training.controller;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.service.PartService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PartControllerTest {

    PartService partServiceMock;
    List<PartDTO> partsMock;

    @Test
    public void getAllPartsTest() {
        partServiceMock = mock(PartService.class); //instantiates mock
        partsMock = (List<PartDTO>) mock(List.class);

        when(partServiceMock.getAllParts()).thenReturn(partsMock);

        PartController p = new PartController(partServiceMock);

        ResponseEntity<List<PartDTO>> check = p.getAllParts();
        assertEquals(check, ResponseEntity.ok(partsMock));

    }

    @Test
    public void getPartByIdTest() {
        partServiceMock = mock(PartService.class); //instantiates mock
        partsMock = (List<PartDTO>) mock(List.class);

        when(partServiceMock.getPartById(1L)).thenReturn(new PartDTO());

        PartController p = new PartController(partServiceMock);

        PartDTO check = p.getPartById(1L).getBody();
        assertEquals(check, new PartDTO());
    }

    @Test
    public void createNewPartTest() {
        partServiceMock = mock(PartService.class); //instantiates mock
        partsMock = (List<PartDTO>) mock(List.class);

        when(partServiceMock.createPart(new PartDTO())).thenReturn(new PartDTO());

        PartController p = new PartController(partServiceMock);

        PartDTO check = p.createNewPart(new PartDTO()).getBody();
        assertEquals(check, new PartDTO());
    }

    @Test
    public void updatePartTest() {
        partServiceMock = mock(PartService.class); //instantiates mock
        partsMock = (List<PartDTO>) mock(List.class);

        when(partServiceMock.updatePart(1L,new PartDTO())).thenReturn(new PartDTO());

        PartController p = new PartController(partServiceMock);

        PartDTO check = p.updatePart(1L, new PartDTO()).getBody();
        assertEquals(check, new PartDTO());
    }

    @Test
    public void getPartByDescriptionTest() {
        partServiceMock = mock(PartService.class); //instantiates mock
        partsMock = (List<PartDTO>) mock(List.class);

        when(partServiceMock.getPartByDescription("tyre")).thenReturn(new PartDTO());

        PartController p = new PartController(partServiceMock);

        PartDTO check = p.getPartByDescription("tyre").getBody();
        assertEquals(check, new PartDTO());
    }
}