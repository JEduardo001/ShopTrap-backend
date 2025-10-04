package com.shoptrap_ecommerce_backend.demo.service;


import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateCategory;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateProduct;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoCategory;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoProduct;
import com.shoptrap_ecommerce_backend.demo.entity.CategoryEntity;
import com.shoptrap_ecommerce_backend.demo.entity.ProductEntity;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNameCategoryAlreadyExist;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotFoundCategory;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotFoundProduct;

import com.shoptrap_ecommerce_backend.demo.repository.RepositoryProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final RepositoryProduct repositoryProduct;

    public ProductService(RepositoryProduct repositoryProduct){
        this.repositoryProduct = repositoryProduct;
    }

    public List<DtoProduct> getAll(Integer page, Integer size){
        Page<ProductEntity> products = repositoryProduct.findAll(PageRequest.of(page,size));

        return products.stream().map(product -> new DtoProduct(
                product.getId(),
                product.getName()
        )).collect(Collectors.toList());
    }

    public DtoProduct create(DtoCreateProduct newProduct){
        ProductEntity product = new ProductEntity();
        product.setName(newProduct.getName());
        repositoryProduct.save(product);

        return new DtoProduct(product.getId(),product.getName());
    }

    public DtoProduct update(DtoProduct newDataProduct){
        ProductEntity product = repositoryProduct.findById(newDataProduct.getId()).orElseThrow(ExceptionNotFoundProduct::new);
        product.setName(newDataProduct.getName());
        repositoryProduct.save(product);

        return newDataProduct;
    }

    public void delete(Long idProduct){
        ProductEntity product = repositoryProduct.findById(idProduct).orElseThrow(ExceptionNotFoundProduct::new);
        repositoryProduct.delete(product);
    }
}
