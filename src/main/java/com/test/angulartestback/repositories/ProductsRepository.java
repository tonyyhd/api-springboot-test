package com.test.angulartestback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.angulartestback.models.Producto;

public interface ProductsRepository extends JpaRepository<Producto, Integer>{

}
