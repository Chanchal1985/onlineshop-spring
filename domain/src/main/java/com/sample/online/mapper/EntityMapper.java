package com.sample.online.mapper;

import com.sample.online.entity.Customer;
import com.sample.online.entity.Product;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityMapper INSTANCE = getMapper( EntityMapper.class );

    Customer pojoToEntity(com.sample.online.domain.Customer customer);
    com.sample.online.domain.Customer entityToPojo(Customer customer);

    Product pojoToEntity(com.sample.online.domain.Product product);

    com.sample.online.domain.Product entityToPojo(Product product);
}
