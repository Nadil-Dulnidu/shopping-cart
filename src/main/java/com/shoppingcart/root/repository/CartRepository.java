package com.shoppingcart.root.repository;

import com.shoppingcart.root.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Integer> {

    List<CartEntity> findByUserId(Integer userId);

    CartEntity findByProductId(Integer productId);
}
