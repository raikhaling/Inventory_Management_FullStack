package com.amnil.invbackend.utils;

import com.amnil.invbackend.dto.core.OrderDto;
import com.amnil.invbackend.entity.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 * The type Custom model mapper.
 */
@Component
public class CustomModelMapper extends ModelMapper {

    /**
     * Instantiates a new Custom model mapper.
     */
    public CustomModelMapper() {
        addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                skip(destination.getOrderItems());
            }
        });
    }
}

