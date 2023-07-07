package com.ecommerce.springbotecommerce.dao;

import com.ecommerce.springbotecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface ProductRepository extends JpaRepository<Product,Long> {
}
