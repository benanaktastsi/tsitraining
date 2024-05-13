package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.entity.Part;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.mapper.PartMapperImpl;
import com.tsi.training.repository.PartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreatePartTest {
    private PartRepository partRepositoryMock;
    private PartMapper partMapperMock;
    private PartService partService;

    @Before
    public void setup() {
        partRepositoryMock = mock(PartRepository.class);
        partMapperMock = mock(PartMapper.class);
        partService = new PartService(partRepositoryMock, partMapperMock);
    }

    @Test
    public void whenSavePart_shouldReturnPart() {
        // Arrange
        PartDTO partdto = new PartDTO(1L, "test", 12.00);
        Part part = new Part(1L, "test", 12.00);
        when(partMapperMock.toEntity(partdto)).thenReturn(part);
        when(partRepositoryMock.save(part)).thenReturn(part);
        when(partMapperMock.toDto(part)).thenReturn(partdto);

        // Act
        PartDTO created = partService.createPart(partdto);

        // Assert
        assertNotNull(created);
        assertEquals(partdto.getId(), created.getId());
        assertEquals(partdto.getDescription(), created.getDescription());
        assertEquals(partdto.getPrice(), created.getPrice());
    }
}