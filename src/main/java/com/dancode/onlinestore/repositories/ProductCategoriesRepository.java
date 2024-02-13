package com.dancode.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dancode.onlinestore.entities.ProductCategories;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategories,Long>{

}
