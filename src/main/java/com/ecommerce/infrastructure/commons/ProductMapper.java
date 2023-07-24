package com.ecommerce.infrastructure.commons;

import com.ecommerce.application.response.ProductResponse;
import com.ecommerce.infrastructure.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
/**
 *
 * @author jfvaldez
 */
@Mapper
public interface ProductMapper {

    @Mapping(source = "entity.brand.id", target = "brandId")
    ProductResponse toResponse(Product entity);


    default LocalDateTime mapTimestamp(Timestamp timestamp) {
        return timestamp.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
