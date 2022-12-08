package com.springboot.relationship.data.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class QProductRepositoryTest {
    @Autowired
    QProductRepository qProductRepository;

    @Test
    public void queryDSLTest1() {
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000, 2500));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if(foundProduct.isPresent()) {
            Product p = foundProduct.get();
            System.out.println(p.getName());
            System.out.println(p.getNumber());
            System.out.println(p.getPrice());
            System.out.println(p.getStock());
        }
    }

    @Test
    public void queryDSLTest2() {
        QProduct qProduct = QProduct.product;

        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("팬")
                        .and(qProduct.price.between(550,1500))
        );

        for(Product p : productList) {
            System.out.println(p.getName());
            System.out.println(p.getNumber());
            System.out.println(p.getPrice());
            System.out.println(p.getStock());
        }
    }
}
