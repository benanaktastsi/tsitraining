package com.tsi.training.util;

import com.tsi.training.dto.response.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProcessResponse {
    List<String> parts;
    List<OrderDTO> orders;

    public ProcessResponse(List<OrderDTO> orders, List<String> parts){
        this.parts = parts;
        this.orders = orders;
    }
}
