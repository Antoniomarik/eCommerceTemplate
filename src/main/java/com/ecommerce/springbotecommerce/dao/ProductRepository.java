package com.ecommerce.springbotecommerce.dao;

import com.ecommerce.springbotecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
