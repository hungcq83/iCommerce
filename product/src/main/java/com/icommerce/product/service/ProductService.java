package com.icommerce.product.service;

import com.icommerce.product.entity.ProductEntity;
import com.icommerce.product.entity.SkuEntity;
import com.icommerce.product.model.request.ProductDTO;
import com.icommerce.product.model.request.SkuDTO;
import com.icommerce.product.repository.ProductRepository;
import com.icommerce.product.repository.SkuRepository;
import com.icommerce.product.specification.ProductSpecifications;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    Logger log = LoggerFactory.getLogger(ProductService.class);

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private AuditService auditService;

    @Autowired
    private EntityManager entityManager;

    @Value("${product.page.size}")
    private int pageSize;

    @Transactional
    public long createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
        productRepository.save(productEntity);

        log.info("Successfully saved new product entity with id: {}", productEntity.getId());

        return productEntity.getId();
    }

    public ProductDTO findProduct(Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);

        if (productEntityOptional.isPresent()) {
            ProductEntity product = productEntityOptional.get();
            auditService.createViewAudit(product.getCode());
            return mapper.map(productEntityOptional.get(), ProductDTO.class);
        }

        return null;
    }

    public List<ProductDTO> findProducts(String query, String filter, String sortBy,
                                   String order, Integer page) {

        Specification<ProductEntity> specification = ProductSpecifications.search("%" + query + "%");

        if (StringUtils.isNotBlank(filter)) {
            specification = specification.and(ProductSpecifications.filter(filter));
        }

        Stream<ProductEntity> productStream;

        if (StringUtils.isNotBlank(sortBy)) {
            productStream = productRepository.findAll(specification,
                    getSortingAndPaging(sortBy, order, page)).get();
        } else {
            productStream = productRepository.findAll(specification).stream();
        }

        List<ProductDTO> productDTOs = productStream
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        auditService.createSearchAudit(query, filter, sortBy, order, page);

        return productDTOs;
    }

    public List<ProductDTO> findProductsByCategoryCode(String categoryCode) {
        return productRepository.findByCategoryCodeIgnoreCase(categoryCode).stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public long updateProduct(ProductDTO productDTO) {
        ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
        productRepository.save(productEntity);

        log.info("Successfully save/update product with ID: {}", productEntity.getId());
        return productEntity.getId();
    }

    @Transactional
    public void updateSku(SkuDTO skuDTO) {
        SkuEntity skuEntity = mapper.map(skuDTO, SkuEntity.class);
        skuRepository.save(skuEntity);

        log.info("Successfully save/update sku");
    }

//    public SkuDTO getSkuByRevision(long productId, int revision) {
//        AuditReader auditReader = AuditReaderFactory.get(entityManager);
//
//        List<SkuEntity> skuEntities = auditReader.createQuery()
//                .forRevisionsOfEntity(SkuEntity.class, true, true)
//                .add(AuditEntity.id().eq(productId))
//                .getResultList();
//
//        if (skuEntities.size() >= revision) {
//            SkuEntity entity = skuEntities.get(revision-1);
//            return mapper.map(entity, SkuDTO.class);
//        }
//
//        return null;
//    }

    private Pageable getSortingAndPaging(String sortBy, String order, Integer page) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, pageSize,
                Sort.by(StringUtils.isNotBlank(order)
                        ? Sort.Direction.fromString(order) : Sort.Direction.ASC
                        , sortBy));
        return pageable;
    }
}
