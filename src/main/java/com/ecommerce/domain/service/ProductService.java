package com.ecommerce.domain.service;
/**
 *
 * @author jfvaldez
 */
import com.ecommerce.application.request.ProductRequest;
import com.ecommerce.application.response.ProductResponse;

public interface ProductService {

    ProductResponse findByProductIdAndBrandIdAndCurrentDate(ProductRequest productRequest);
}
