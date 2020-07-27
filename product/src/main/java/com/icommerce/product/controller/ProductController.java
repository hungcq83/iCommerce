package com.icommerce.product.controller;

import com.icommerce.product.model.request.ProductDTO;
import com.icommerce.product.model.response.ProductResponseDTO;
import com.icommerce.product.service.ProductService;

import com.icommerce.product.validation.Extended;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}/revision/{revisionId}")
    public ResponseEntity<ProductDTO> getProductByRevision(@NotNull @Min(1) @PathVariable("id") Long id,
                                                           @NotNull @Min(1) @PathVariable("revisionId") Integer revisionId) {

        ProductDTO productDTO = productService.getProductByRevision(id, revisionId);

        if (productDTO != null) {
            log.info("Found revision: {} for product ID: {}", revisionId, id);
            return ResponseEntity.ok(productDTO);
        }

        log.info("Could not find revision: {} for product ID: {}", revisionId, id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> viewProduct(@NotNull @Min(1) @PathVariable("id") Long id) {

        ProductDTO productDTO = productService.findProduct(id);

        if (productDTO != null) {
            log.info("Found product with ID: {}", id);
            return ResponseEntity.ok(productDTO);
        }

        log.info("Could not find product with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findProducts(@RequestParam(name="q")  String query,
                                                  @RequestParam(required = false) String filter,
                                                  @RequestParam(required = false) String sortBy,
                                                  @RequestParam(required = false) String order,
                                                  @RequestParam(required = false) Integer page) {

        List<ProductDTO> products = new ArrayList<>();

        if (StringUtils.isNotBlank(query)) {
            products = productService.findProducts(query, filter, sortBy, order, page);
        }

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        long productId = productService.createProduct(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponseDTO(productId));
    }

    @PutMapping
    @Validated(Extended.class)
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        long productId = productService.updateProduct(productDTO);
        return ResponseEntity.ok(new ProductResponseDTO(productId));
    }
}
