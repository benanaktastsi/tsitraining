package com.tsi.training.service;

import com.tsi.training.dto.response.OrderDTO;
import com.tsi.training.dto.response.OrderItemDTO;
import com.tsi.training.repository.PartRepository;
import com.tsi.training.util.ProcessResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PartServiceTests {
    private PartRepository partRepositoryMock;
    private PartService partService;
    private ProcessResponse processResponse;

    @Before
    public void setup() {
        partRepositoryMock = mock(PartRepository.class);
        partService = mock(PartService.class);

        // Create mock process response
        OrderItemDTO item1 = new OrderItemDTO(0.99, "", "Door");
        OrderItemDTO item2 = new OrderItemDTO(1.99, "", "Engine");
        OrderItemDTO item3 = new OrderItemDTO(10.99, "", "Wheel");

        OrderDTO order1 = new OrderDTO("Volkswagen",
                "123", "",
                "Leo", "07892781112",
                "L19EN", Arrays.asList(item1, item2));

        OrderDTO order2 = new OrderDTO("Hyundai",
                "124", "",
                "Matthew", "07892781192",
                "B377YN", Arrays.asList(item1, item3));

        OrderDTO order3 = new OrderDTO("Ford",
                "127", "",
                "Lisa", "07892786123",
                "S437KD", List.of(item3));

        processResponse = new ProcessResponse(Arrays.asList(order1, order2, order3)
                , Arrays.asList(item1.getPartDescription(), item2.getPartDescription(),
                item3.getPartDescription()));
    }

    @Test
    public void whenPartIsMissingFromDatabase_ThenPartShouldBeRemovedFromOrders_AndEmptyOrdersShouldBeDeleted() {
        // Arrange
        List<String> descriptions = Arrays.asList("Door", "Engine");
        when(partRepositoryMock.findByDescriptionIn(processResponse.getParts()))
                .thenReturn(descriptions);

        // Act
        partService.validateParts(processResponse);

        // Assert
        Assert.assertEquals(2, processResponse.getOrders().size());
        Assert.assertEquals(2, processResponse.getOrders().get(0).getParts().size());
        Assert.assertEquals(1, processResponse.getOrders().get(1).getParts().size());

        for (OrderDTO order : processResponse.getOrders()) {
            for (OrderItemDTO part : order.getParts()) {
                Assert.assertTrue(descriptions.contains(part.getPartDescription()));
            }
        }
    }
}
