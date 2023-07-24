package com.ecommerce.domain.repository;
/**
 *
 * @author jfvaldez
 */
import com.ecommerce.application.request.ProductRequest;
import com.ecommerce.application.response.ProductResponse;

public interface ProductRepository {

    ProductResponse findByProductIdAndBrandIdAndCurrentDate(ProductRequest productRequest);
}
