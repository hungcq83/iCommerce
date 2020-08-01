package com.icommerce.product.repository;

import com.icommerce.product.entity.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends JpaRepository<SkuEntity, Long>, JpaSpecificationExecutor<SkuEntity> {

}
