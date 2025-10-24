package com.test.angulartestback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.angulartestback.models.Producto;
import com.test.angulartestback.services.ProductService;

@RestController
@RequestMapping("/")
@PreAuthorize("hasRole('test')")
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("productos")
    public List<Producto> getsProducts(){
        List<Producto> productos = this.productService.getProducts();
        return productos;
    }

    @GetMapping("producto")
    public ResponseEntity<Producto> getProduct(
        @RequestParam int id)
        {
            Producto producto = this.productService.getProduct(id);

            if(producto != null){
                return ResponseEntity.ok(producto);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }

    @PostMapping("crear/producto")
    public ResponseEntity<Producto> createProduct(
        @RequestBody Producto productoRequest)
        {
                if(productoRequest!= null){
                    Producto producto = new Producto();
                    producto.setDescripcion(productoRequest.getDescripcion());
                    producto.setPrecio(productoRequest.getPrecio());
                    producto.setExistencia(productoRequest.getExistencia());
                    this.productService.saveProduct(producto);
                    return ResponseEntity.ok(producto);
                }
                else {
                    return ResponseEntity.notFound().build();
                }
        }

    @PutMapping("actualizar/producto")
    public ResponseEntity<Producto> updateProduct(@RequestBody Producto productoRequest) {
        if (productoRequest.getIdProducto() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Producto producto = this.productService.getProduct(productoRequest.getIdProducto());

        if (producto != null) {
            producto.setDescripcion(productoRequest.getDescripcion());
            producto.setPrecio(productoRequest.getPrecio());
            producto.setExistencia(productoRequest.getExistencia());
            this.productService.saveProduct(producto);
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("borrar/producto")
    public ResponseEntity<Producto> removeProduct(@RequestParam int id) {
        Producto producto = this.productService.getProduct(id);

        if(producto != null){
            this.productService.deleteProduct(producto);
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

}
