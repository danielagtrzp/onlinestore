package com.dancode.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dancode.onlinestore.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

}
