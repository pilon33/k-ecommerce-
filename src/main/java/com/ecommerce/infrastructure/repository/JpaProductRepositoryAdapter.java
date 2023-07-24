package com.ecommerce.infrastructure.repository;

import com.ecommerce.application.request.ProductRequest;
import com.ecommerce.application.response.ProductResponse;
import com.ecommerce.domain.repository.ProductRepository;
import com.ecommerce.infrastructure.commons.ProductMapper;

import com.ecommerce.infrastructure.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author jfvaldez
 */
@Component
public class JpaProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    private static Logger logger = LoggerFactory.getLogger(JpaProductRepositoryAdapter.class);

    @Autowired
    public JpaProductRepositoryAdapter(JpaProductRepository jpaProductRepository, ProductMapper productMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse findByProductIdAndBrandIdAndCurrentDate(ProductRequest productRequest) {
        ProductResponse response = new ProductResponse();
        try {
            List<Product> productEntities = jpaProductRepository.findByProductIdAndBrandIdAndCurrentDate(
                    productRequest.getProductId(), productRequest.getBrandId(), productRequest.getCurrentDate());
            response = this.retrieveMaxPriority(productEntities);

        } catch (Exception e) {
            logger.error("Error en BBDD por: " + e.getMessage());
        }
        return response;
    }

    private ProductResponse retrieveMaxPriority(List<Product> productEntities){
        Optional<Product> entity = productEntities.stream().findFirst();
        ProductResponse response = new ProductResponse();
        if(entity.isPresent()){
            response =productMapper.toResponse(entity.get());
        }else{
            logger.info("there is no chosen value");
        }
        return response;
    }

}
