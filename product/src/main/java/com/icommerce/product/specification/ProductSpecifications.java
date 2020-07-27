package com.icommerce.product.specification;

import com.icommerce.product.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public interface ProductSpecifications {

    static Specification<ProductEntity> filter(String filter) {
        String[] arr = filter.split(":");
        String filterKey = arr[0];
        String filterValue = arr[1];

        return (product, cq, cb) -> cb.like(
                cb.lower(product.get(filterKey)), filterValue.toLowerCase());
    }

    static Specification<ProductEntity> search(String query) {
        return (product, cq, cb) -> cb.like(cb.lower(product.get("name")), query.toLowerCase());
    }

}
