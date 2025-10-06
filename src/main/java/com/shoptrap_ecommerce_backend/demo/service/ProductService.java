package com.shoptrap_ecommerce_backend.demo.service;


import com.shoptrap_ecommerce_backend.demo.dto.DtoFilter;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateCategory;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateProduct;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoCategory;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoProduct;
import com.shoptrap_ecommerce_backend.demo.entity.CategoryEntity;
import com.shoptrap_ecommerce_backend.demo.entity.ProductEntity;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNameCategoryAlreadyExist;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotFoundCategory;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotFoundProduct;

import com.shoptrap_ecommerce_backend.demo.projection.ProductProjection;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryCategory;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final RepositoryProduct repositoryProduct;
    private final RepositoryCategory repositoryCategory;

    public ProductService(RepositoryProduct repositoryProduct,RepositoryCategory repositoryCategory){
        this.repositoryProduct = repositoryProduct;
        this.repositoryCategory = repositoryCategory;
    }

    public Page<ProductProjection> getAll(Integer page, Integer size){
        return repositoryProduct.findAllByProjection(PageRequest.of(page,size));
    }

    public List<ProductProjection> filtersToApply(Integer page, Integer size, DtoFilter filtersToApply){
        repositoryCategory.findById(filtersToApply.getIdCategory()).orElseThrow(ExceptionNotFoundCategory::new);

        Page<ProductProjection> products = repositoryProduct.findAllByProjection(PageRequest.of(page,size));

        products.getContent().stream().map(p -> p).forEach(p ->         System.out.println("objeto oooo: " + p));
        //filter by categories
        List<ProductProjection> productFiltredByCategory = products.getContent().stream()
                .filter(p -> p.getCategory().stream()
                        .anyMatch(c -> c.getId().equals(filtersToApply.getIdCategory())))
                .toList();
        //filter if product has a discount
        List<ProductProjection> productFilteredIfHaveDiscount = productFiltredByCategory.stream().filter(p -> p.getHasDiscount() == filtersToApply.getDiscount()).toList();

        BigDecimal filterPrice = filtersToApply.getPrice();
        return productFilteredIfHaveDiscount.stream()
                .filter(p -> p != null
                        && p.getPrice() != null
                        && filterPrice != null
                        && p.getPrice().compareTo(filterPrice) <= 0)
                .toList();

    }

    public DtoProduct create(DtoCreateProduct newProduct){
        //search if any category does not exist
        List<Long> categoryIds = newProduct.getCategory().stream()
                .map(CategoryEntity::getId)
                .toList();

        List<CategoryEntity> foundCategories = repositoryCategory.findAllById(categoryIds);

        if (foundCategories.size() != categoryIds.size()) {
            throw new ExceptionNotFoundCategory();
        }


        ProductEntity product = new ProductEntity();
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setDicount(newProduct.getDiscount());
        product.setDateCreated(LocalDateTime.now());
        product.setCategory(newProduct.getCategory());
        product.setHasDiscount(newProduct.getHasDiscount());
        repositoryProduct.save(product);

        DtoProduct dtoProduct = new DtoProduct();
        dtoProduct.setId(product.getId());
        dtoProduct.setPrice(product.getPrice());
        dtoProduct.setDateCreated(product.getDateCreated());
        dtoProduct.setDiscount(product.getDicount());
        dtoProduct.setStock(product.getStock());

        return dtoProduct;
    }


    public DtoProduct update(DtoProduct newDataProduct){
        ProductEntity product = repositoryProduct.findById(newDataProduct.getId()).orElseThrow(ExceptionNotFoundProduct::new);
        product.setName(newDataProduct.getName());
        product.setPrice(newDataProduct.getPrice());
        product.setDicount(newDataProduct.getDiscount());
        product.setStock(newDataProduct.getStock());

        repositoryProduct.save(product);

        return newDataProduct;
    }

    public void delete(Long idProduct){
        ProductEntity product = repositoryProduct.findById(idProduct).orElseThrow(ExceptionNotFoundProduct::new);
        repositoryProduct.delete(product);
    }
}
