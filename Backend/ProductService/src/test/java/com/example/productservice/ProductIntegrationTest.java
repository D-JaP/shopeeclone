package com.example.productservice;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.security.RsaKeyProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:/sql_script/product_insert_data.sql")
@Sql(scripts = "classpath:/sql_script/product_cleanup_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableConfigurationProperties({RsaKeyProperties.class})
@SpringBootTest
public class ProductIntegrationTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testProductRepo() {
        Product product = productRepository.findById(100000L).get();
        Assertions.assertThat(product.getName()).isEqualTo("test_name");
        Assertions.assertThat(product.getPrice()).isEqualTo(123);
        Assertions.assertThat(product.getDescription()).isEqualTo("test description");
    }
}
