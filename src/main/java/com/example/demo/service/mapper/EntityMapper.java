package com.example.demo.service.mapper;

import java.util.List;

/*  <D> DTO type paramester
*   <E> Entity type paramester
*
* */

public interface EntityMapper<D, E> {
    E toEntity(D DTO);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

}
