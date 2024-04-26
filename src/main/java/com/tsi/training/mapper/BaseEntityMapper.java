package com.tsi.training.mapper;

import com.tsi.training.dto.BaseDTO;
import com.tsi.training.entity.BaseEntity;
import org.mapstruct.MappingTarget;

import java.util.List;


public interface BaseEntityMapper<D extends BaseDTO, E extends BaseEntity> {

    E toEntity(D dto);

    E toEntity(D dto, @MappingTarget E entity);

    E toEntity(E entitySource, @MappingTarget E entity);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
