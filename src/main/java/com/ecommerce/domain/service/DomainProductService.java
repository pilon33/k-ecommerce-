package com.ecommerce.domain.service;
/**
 *
 * @author jfvaldez
 */
import com.ecommerce.application.request.ProductRequest;
import com.ecommerce.application.response.ProductResponse;
import com.ecommerce.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DomainProductService implements ProductService{

    private final ProductRepository productRepository;

    public DomainProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse findByProductIdAndBrandIdAndCurrentDate(ProductRequest productRequest) {
        return productRepository.findByProductIdAndBrandIdAndCurrentDate(productRequest);
    }
}
