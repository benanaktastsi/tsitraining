package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.repository.PartRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


public class CreatePartTest {

    private PartService partService;
    @Before
    public void setup() {
        PartRepository partRepositoryMock = mock(PartRepository.class);
        PartMapper partMapperMock = mock(PartMapper.class);
        partService = new PartService(partRepositoryMock, partMapperMock);
    }
    @Test
    public void whenSavePart_shouldReturnPart() {
        PartDTO partdto = new PartDTO(1L, "test", 12.00);
        PartDTO created = partService.createPart(partdto);
        assertNotNull(created);
        assertEquals(partdto.getId(), created.getId());
        assertEquals(partdto.getDescription(), created.getDescription());
        assertEquals(partdto.getPrice(), created.getPrice());
    }
}