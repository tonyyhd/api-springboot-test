package com.test.angulartestback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.angulartestback.interfaces.ProductsInterface;
import com.test.angulartestback.models.Producto;
import com.test.angulartestback.repositories.ProductsRepository;

@Service
public class ProductService implements ProductsInterface {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Producto> getProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Producto getProduct(Integer idProducto) {
       return productsRepository.findById(idProducto).orElse(null);
    }

    @Override
    public void saveProduct(Producto producto) {
        productsRepository.save(producto);
    }

    @Override
    public void deleteProduct(Producto producto) {
        productsRepository.delete(producto);
    }

}
