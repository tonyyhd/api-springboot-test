package com.test.angulartestback.interfaces;

import java.util.List;

import com.test.angulartestback.models.Producto;

public interface ProductsInterface {

        public List<Producto> getProducts();
        public Producto getProduct(Integer idProducto);
        public void saveProduct(Producto producto);
        public void deleteProduct(Producto producto); 
    
}
