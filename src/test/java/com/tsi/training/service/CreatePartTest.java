package com.tsi.training.service;

import com.tsi.training.dto.PartDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreatePartTest {
    @MockBean
    private PartService partService;

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