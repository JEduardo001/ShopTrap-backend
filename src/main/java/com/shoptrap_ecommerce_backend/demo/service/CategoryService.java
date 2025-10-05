package com.shoptrap_ecommerce_backend.demo.service;

import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateCategory;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoCategory;
import com.shoptrap_ecommerce_backend.demo.entity.CategoryEntity;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNameCategoryAlreadyExist;
import com.shoptrap_ecommerce_backend.demo.exception.personalityException.ExceptionNotFoundCategory;
import com.shoptrap_ecommerce_backend.demo.repository.RepositoryCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final RepositoryCategory repositoryCategory;

    public CategoryService(RepositoryCategory repositoryCategory){
        this.repositoryCategory = repositoryCategory;
    }

    public List<DtoCategory> getAll(){
        List<CategoryEntity> categories = repositoryCategory.findAll();

        return categories.stream().map(category -> new DtoCategory(
                category.getId(),
                category.getName(),
                category.getCantProducts()
        )).collect(Collectors.toList());
    }

    public DtoCategory create(DtoCreateCategory newCategory){
        if(repositoryCategory.findByName(newCategory.getName()).isPresent()){
            throw new ExceptionNameCategoryAlreadyExist();
        }
        CategoryEntity category = new CategoryEntity();
        category.setName(newCategory.getName());
        repositoryCategory.save(category);

        return new DtoCategory(category.getId(),category.getName(),category.getCantProducts());
    }

    public DtoCategory update(DtoCategory newDataCategory){
        CategoryEntity category = repositoryCategory.findById(newDataCategory.getId()).orElseThrow(ExceptionNotFoundCategory::new);
        category.setName(newDataCategory.getName());
        category.setCantProducts(newDataCategory.getCantProducts());
        repositoryCategory.save(category);

        return newDataCategory;
    }

    public void delete(Long idCategory){
        CategoryEntity category = repositoryCategory.findById(idCategory).orElseThrow(ExceptionNotFoundCategory::new);
        repositoryCategory.delete(category);
    }
}
