package com.tsi.training.service;


import com.tsi.training.dto.PartDTO;
import com.tsi.training.entity.Part;
import com.tsi.training.mapper.PartMapper;
import com.tsi.training.repository.PartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreatePartTest {
    @Mock
    private PartRepository partRepository;
    @Mock
    private PartMapper partMapper;
    @InjectMocks
    private PartService partService;
    @Test
    public void whenSavePart_shouldReturnPart() {
        PartDTO partdto = new PartDTO(1L, "test", 12.00);
        PartDTO created = partService.createPart(partdto);
        //verify conversion to entity
        verify(partMapper).toEntity(partdto);
        //verify entity gets saved
        verify(partRepository).save(partMapper.toEntity(partdto));
        //verify entity gets returned
        assertNotNull(created);
        assertEquals(partdto.getId(), created.getId());
        assertEquals(partdto.getDescription(), created.getDescription());
        assertEquals(partdto.getPrice(), created.getPrice());
        //TODO: is this a problem with mock setup or with implementation?
    }
}