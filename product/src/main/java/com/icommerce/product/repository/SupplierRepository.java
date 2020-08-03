package com.icommerce.product.repository;

import com.icommerce.product.entity.SkuEntity;
import com.icommerce.product.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

}
