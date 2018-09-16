package com.sample.online.mapper;

import com.sample.online.entity.Customer;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityMapper INSTANCE = getMapper( EntityMapper.class );

    Customer pojoToEntity(com.sample.online.domain.Customer customer);
    com.sample.online.domain.Customer entityToPojo(Customer customer);
}
