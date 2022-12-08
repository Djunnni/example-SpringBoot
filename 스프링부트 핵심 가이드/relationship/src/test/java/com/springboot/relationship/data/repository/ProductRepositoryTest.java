package com.springboot.relationship.data.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Test
    void sortingAndPagingTest() {
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(1000);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        System.out.println(productRepository.findByName("펜", getSort()));

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));

        System.out.println(productPage.getContent());
    }

    private Sort getSort() {
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query.from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product p : productList) {
            System.out.println("============================");
            System.out.println();
            System.out.println("Product Number : " + p.getNumber());
            System.out.println("Product Name : " + p.getName());
            System.out.println("Product Price : " + p.getPrice());
            System.out.println("Product Stock : " + p.getStock());
            System.out.println();
            System.out.println("============================");
        }
    }

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Test
    void queryDslTest4() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList) {
            System.out.println("=========================");
            System.out.println("Product Name : " + product);
            System.out.println("=========================");
        }
    }

    @Test
    void findByNameTest() {
        List<Product> productList = productRepository.findByName("펜");

        for(Product product : productList) {
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }
    @Test
    public void auditingTest() {
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(100);

        Product savedProduct = productRepository.save(product);

        System.out.println("ProductName :" + savedProduct.getName());
        System.out.println("createdAt : " + savedProduct.getCreatedAt());
    }
}
