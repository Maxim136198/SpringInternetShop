package by.itstep.springInternetShop.unit.service;

import by.itstep.springInternetShop.dao.repository.ProductRepository;
import by.itstep.springInternetShop.dao.entity.Product;
import by.itstep.springInternetShop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

//    Product save(Product product);
//    void delete(Product product);
//    void deleteById(Long id);
//    Product updateProduct(Product product);
//    List<Product> findAll();
//    Product findById(Long id);
//    Product findByName(String name);

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    private void testSave(){
        final Product product = new Product();
        when(productRepository.saveAndFlush(product)).thenReturn(product);
        assertEquals(productService.save(product), product);
    }

    @Test
    private void testDelete(){
        final Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);
        assertDoesNotThrow(() -> productService.delete(product));
    }

    @Test
    private void testDeleteById(){
        final Product product = new Product();
        product.setId(1L);
        doNothing().when(productRepository).deleteById(any(Long.class));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertDoesNotThrow(() -> productService.deleteById(1L));
    }

    @Test
    private void testUpdateProduct(){
        final Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.saveAndFlush(product)).thenReturn(product);
        assertEquals(productService.updateProduct(product), product);
    }

    @Test
    private void testFindAll(){
        final List<Product> productList = Collections.singletonList(new Product());
        when(productRepository.findAll()).thenReturn(productList);
        assertEquals(productService.findAll(), productList);
    }

    @Test
    private void testFindById(){
        final Product product = new Product();
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        assertEquals(productService.findById(1L), product);
    }

    @Test
    private void testFindByName(){
        final Product product = new Product();
        when(productRepository.findByName(any(String.class))).thenReturn(Optional.of(product));
        assertEquals(productService.findByName("milk"), product);
    }
}
