package com.springboot.relationship.data.repository;

import com.google.common.collect.Lists;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {
    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void relationshipTest1() {
        Provider provider = new Provider();
        provider.setName("00물산");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        System.out.println("product: " + productRepository.findById(1L).orElseThrow(RuntimeException::new));

        System.out.println("provider: "+ productRepository.findById(1L).orElseThrow(RuntimeException::new).getProvider());
    }
    @Test
    void relationshipTest() {
        Provider provider = new Provider();
        provider.setName("00상사");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("펜");
        product.setPrice(2000);
        product.setStock(100);
        product.setProvider(provider);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(2000);
        product2.setStock(200);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3000);
        product3.setStock(1000);
        product3.setProvider(provider);


        productRepository.save(product);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();

        for(Product p : products) {
            System.out.println(p);
        }
    }

    @Test
    void cascadeTest(){
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000, 1000);
        Product product2 = savedProduct("상품2", 500, 500);
        Product product3 = savedProduct("상품3", 700, 600);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));
        providerRepository.save(provider);
    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    @Test
    @Transactional
    void orphanRemovalTest() {
        Provider provider = savedProvider("새로운 공급업체");
        Product product1 = savedProduct("상품1", 1000, 1000);
        Product product2 = savedProduct("상품2", 500, 500);
        Product product3 = savedProduct("상품3", 700, 600);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);
    }
}
