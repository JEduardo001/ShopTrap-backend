package com.shoptrap_ecommerce_backend.demo.controller;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateProduct;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateUser;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoProduct;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoUser;
import com.shoptrap_ecommerce_backend.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<DtoApiResponse> getAll(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Productos obtenidos",productService.getAll(page,size))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<DtoApiResponse> create(@Valid @RequestBody DtoCreateProduct newProduct){
        productService.create(newProduct);
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.CREATED.value(),"Producto Creado")
        );
    }

    @PostMapping("/update/{idProduct}")
    public ResponseEntity<DtoApiResponse> updateUser(@Valid @RequestBody DtoProduct newDataProduct){
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Producto actualizado", productService.update(newDataProduct))
        );
    }


    @DeleteMapping("/delete/{idProduct}")
    public ResponseEntity<DtoApiResponse> deleteUser(@PathVariable Long idProduct){
        productService.delete(idProduct);
        return ResponseEntity.ok(
                new DtoApiResponse(HttpStatus.OK.value(),"Producto eliminado")
        );
    }
}
