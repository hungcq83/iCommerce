package com.icommerce.product.service;

import com.icommerce.product.entity.ProductEntity;
import com.icommerce.product.model.request.ProductDTO;
import com.icommerce.product.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AuditService auditService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindProductWithNonExistingId_thenReturnNull() {
        when(productRepository.findById(1l)).thenReturn(Optional.ofNullable(null));
        ProductDTO dto = productService.findProduct(1l);
        assertNull(dto);
    }

    @Test
    public void whenFindProductWithExistingId_thenReturnCorrectDTO() {
        ProductEntity productEntity = new ProductEntity().builder()
                .brand("Apple")
                .code("123")
                .name("New Product")
                .build();
        when(productRepository.findById(1l)).thenReturn(Optional.of(productEntity));
        ProductDTO dto = productService.findProduct(1l);
        assertNotNull(dto);
        assertEquals(productEntity.getBrand(), dto.getBrand());
        assertEquals(productEntity.getCode(), dto.getCode());
        assertEquals(productEntity.getName(), dto.getName());
    }

}
