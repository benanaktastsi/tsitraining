package com.tsi.training.mapper;

import com.tsi.training.dto.PartDTO;
import com.tsi.training.entity.Part;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartMapper extends BaseEntityMapper<PartDTO, Part> {
}
